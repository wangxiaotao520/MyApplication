package com.myapplication.widget;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class Charts {
  
    //坐标绘图  
    private LineChartView lineChart;  
    private LineChartData data = new LineChartData();
    private List<Line> lines = new ArrayList<Line>();

    String[] date = {"05/01","05/02","05/03","05/04","05/05","05/06","05/07","05/08","05/09","05/10","05/11","05/12","05/13","05/14","05/15","05/16","05/17","05/18","05/19","05/20","05/21","05/22","05/23","05/24","05/25","05/26","05/27","05/28","05/29","05/30"};//X轴的标注
    int[] score= {50,42,90,33,10,74,22,18,79,20,50,42,90,33,10,74,22,18,79,20,50,42,90,33,10,74,22,18,79,20};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<AxisValue> mAxisYValues = new ArrayList<AxisValue>();

    public Charts(LineChartView lineChart) {
//        //设置x轴y轴长度，间隔
//        int xLength = 600;
//        int yLength = 300;
//        int subX = 20;
//        int subY = 20;
//
//        this.lineChart = lineChart;
//        data.setLines(lines);
//
//        //设置X坐标轴
//        Axis axisX = new Axis();
//        axisX.setTextSize(10);//字体大小
//        axisX.setTextColor(Color.rgb(55, 93, 93));  //字体颜色
//        List<AxisValue> Xtab = new ArrayList<AxisValue>();
//        for (int i = 0; i < xLength; i += subX) {
//            Xtab.add(new AxisValue(i).setLabel("" + i));
//        }
//        axisX.setValues(Xtab);  //填充X轴的坐标名称
//        axisX.setHasLines(true); //x 轴分割线
//        axisX.setName("力值 N");
//
//        //设置Y坐标轴
////        Axis axisY = new Axis();
////        axisY.setTextColor(Color.rgb(55, 93, 93));  //字体颜色
////        List<AxisValue> Ytab = new ArrayList<AxisValue>();
////        for (int i = 0; i < yLength; i += subY) {
////            Ytab.add(new AxisValue(i).setLabel("" + i));
////        }
////        axisY.setValues(Ytab);  //填充X轴的坐标名称
////        axisY.setTextSize(10);//设置字体大小
////        axisY.setHasLines(true); //Y 轴分割
////        axisY.setName("高度 mm");
////        data.setAxisXBottom(axisX); //x 轴在底部
////        data.setAxisYLeft(axisY);  //Y轴设置在左边
//
//        //将坐标轴固定
//        lineChart.setViewportCalculationEnabled(false);
//        Viewport v = new Viewport(lineChart.getMaximumViewport());
//        v.bottom = 0;
//        v.top = yLength;
//        v.left = 0;
//        v.right = xLength;
//        lineChart.setMaximumViewport(v);
//        lineChart.setCurrentViewport(v);
//        lineChart.setZoomEnabled(false);
        this.lineChart = lineChart;
        getAxisXLables();
        getAxisYLables();
        getAxisPoints();
        initLineChart();
    }  
  
    public void flush() {  
        lineChart.setLineChartData(data);  
    }  
  
    public class LINE {  
        List<PointValue> Xval = new ArrayList<PointValue>();
        Line line = new Line(Xval);   
  
        public LINE(String color) {  
            line.setColor(Color.parseColor(color));  
            line.setStrokeWidth(2);  
            line.setCubic(true);//是否平滑  
            line.setHasLabelsOnlyForSelected(true);  
            line.setHasLines(true);//是否用线显示  
            line.setHasPoints(false);//是否显示圆点  
            lines.add(line);  
        }  
  
        public void add(float x, float y) {  
            Xval.add(new PointValue(x, y));  
        }  
  
        public void clean() {  
            Xval.clear();  
        }  
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }
    /**
     * 设置X 轴的显示
     */
    private void getAxisYLables() {
        for (float i = 0; i <= 100; i += 20) {
            mAxisYValues.add(new AxisValue(i).setLabel(i + ""));
        }

    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(){
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }

    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFFFFF"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(1);
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setPointRadius(3);
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);//此处设置坐标点旁边的文字背景
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelsTextColor(Color.WHITE);  //此处设置坐标点旁边的文字颜色
        data.setValueLabelTextSize(10);
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        //TODO 这个方法到现在我都没搞明白
        axisX.setMaxLabelChars(0); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setValues(mAxisYValues);
    //    axisY.setMaxLabelChars(8);
        axisY.setTextColor(Color.GRAY);
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
     //   lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setZoomEnabled(false);
        lineChart.setMaxZoom((float) 4);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
       // lineChart.setViewportCalculationEnabled(false);
        Viewport v = new Viewport(lineChart.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 100;
        v.bottom = 0;
        v.top = 100;
        lineChart.setMaximumViewport(v);
        v.left=14;
        v.right=20;
        lineChart.setCurrentViewport(v);
    }
}