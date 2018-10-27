package com.myapplication.utils.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.myapplication.utils.NullUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Lydia on 2018/5/22 0028.
 */
public class FrescoUtils {
    private ExecutorService executeBackgroundTask = Executors.newSingleThreadExecutor();

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public FrescoUtils() {
    }

    private static class FrescoUtilsHolder {
        private final static FrescoUtils INSTANCE = new FrescoUtils();
    }

    public static FrescoUtils getInstance() {
        return FrescoUtilsHolder.INSTANCE;
    }

	/**
     * 初始化（建议在application）
     * @param context
     */
    public void initializeFresco(Context context){
        Fresco.initialize(context);
    }

    /**
     * 显示图片
     * @param simpleDraweeView
     * @param url
     */
    public void setImageUri(SimpleDraweeView simpleDraweeView, String url){
        if (!NullUtil.isStringEmpty(url)) {
            if (url.contains(".gif")) {
                DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(Uri.parse(url))
                        .build();
                simpleDraweeView.setController(mDraweeController);
            } else {
                simpleDraweeView.setImageURI(Uri.parse(url));
            }
        }
    }

    /**
     * 加载直接返回Bitmap
     */
    public final void loadImageBitmap(String url, FrescoBitmapCallback<Bitmap> callback) {
        if (NullUtil.isStringEmpty(url)) {
            return;
        }
        try {
            fetch(Uri.parse(url), callback);
        } catch (Exception e) {
            //OOM风险.
            e.printStackTrace();
            callback.onFailure(Uri.parse(url), e);
        }
    }

    private void fetch(final Uri uri, final FrescoBitmapCallback<Bitmap> callback) throws Exception {
        ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        ImageRequest imageRequest = requestBuilder.build();
        DataSource<CloseableReference<CloseableImage>> dataSource = ImagePipelineFactory.getInstance()
                .getImagePipeline().fetchDecodedImage(imageRequest, null);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
                                 @Override
                                 public void onNewResultImpl(@Nullable final Bitmap bitmap) {
                                     if (callback == null)
                                         return;
                                     if (bitmap != null && !bitmap.isRecycled()) {
                                         handlerBackgroundTask(new Callable<Bitmap>() {
                                             @Override
                                             public Bitmap call() throws Exception {
                                                 final Bitmap resultBitmap = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
                                                 if (resultBitmap != null && !resultBitmap.isRecycled())
                                                     postResult(resultBitmap, uri, callback);
                                                 return resultBitmap;
                                             }
                                         });
                                     }
                                 }

                                 @Override
                                 public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
                                     super.onCancellation(dataSource);
                                     if (callback == null)
                                         return;
                                     callback.onCancel(uri);
                                 }

                                 @Override
                                 public void onFailureImpl(DataSource dataSource) {
                                     if (callback == null)
                                         return;
                                     Throwable throwable = null;
                                     if (dataSource != null) {
                                         throwable = dataSource.getFailureCause();
                                     }
                                     callback.onFailure(uri, throwable);
                                 }
                             },
                UiThreadImmediateExecutorService.getInstance());
    }

    /**
     * @param callable Callable
     * @param <T>      T
     * @return Future
     */
    private <T> Future<T> handlerBackgroundTask(Callable<T> callable) {
        return executeBackgroundTask.submit(callable);
    }

    /**
     * 回调UI线程中去
     * @param result   result
     * @param uri      uri
     * @param callback FrescoBitmapCallback
     * @param <T>      T
     */
    private <T> void postResult(final T result, final Uri uri, final FrescoBitmapCallback<T> callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(uri, result);
            }
        });
    }
}