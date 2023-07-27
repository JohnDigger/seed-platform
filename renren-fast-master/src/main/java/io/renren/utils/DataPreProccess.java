package io.renren.utils;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 贾佳
 * @date 2023/5/5 20:59
 */
@Component
public class DataPreProccess {
    //    public static void main(String[] args) {
//        DataPreProccess dataPreProccess = new DataPreProccess();
////        double[] encodedData =  dataPreProccess.OneHotEncode();
//        double[] encodedData =  {1.0, 0.0, 1.0, 0.0, 0.0};
//        // 数值型特征数据
//        double[] featureValues = {8, 38.26, 15274.94, 6, 49, 49.12, 0.52, 84.13, 61.33};
//
//        // 归一化处理数值型特征
//
//
//    }
    public Double SimpleLineCalculate(double[] encodedData, double[] featureValues) {
        double[] normalizedFeatures = normalizeFeatures(featureValues);

        // 合并独热编码特征和归一化后的数值型特征
        double[] combinedFeatures = combineFeatures(encodedData, normalizedFeatures);

        // 输出的目标变量（良种率）数据
        // 创建简单线性回归模型
        SimpleRegression regression = new SimpleRegression();

        // 添加训练数据
        for (int i = 0; i < featureValues.length; i++) {
            regression.addData(featureValues[i], combinedFeatures[i]);
        }

        // 获取回归模型的参数
        double intercept = regression.getIntercept();
        double slope = regression.getSlope();

        // 打印回归模型的参数
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);

        // 预测新的数据
        double newFeature = 12.0; // 新的特征值
        double predictedTarget = regression.predict(newFeature);

        // 打印预测结果
        System.out.println("Predicted Target for New Feature: " + predictedTarget);
        return predictedTarget;
    }

    public double[] OneHotEncode(String selectedOption1, String selectedOption2) {
//        String selectedOption1 = "软"; // 假设从前端接收到的选项1是"软"
//        String selectedOption2 = "高"; // 假设从前端接收到的选项2是"高"

// 特征1的独热编码
        double[] encodedData1 = new double[2]; // 假设有两个选项：软、硬
        if (selectedOption1.equals("软")) {
            encodedData1[0] = 1;
        } else if (selectedOption1.equals("硬")) {
            encodedData1[1] = 1;
        }

// 特征2的独热编码
        double[] encodedData2 = new double[3]; // 假设有三个选项：高、中、低
        if (selectedOption2.equals("高")) {
            encodedData2[0] = 1;
        } else if (selectedOption2.equals("中")) {
            encodedData2[1] = 1;
        } else if (selectedOption2.equals("低")) {
            encodedData2[2] = 1;
        }

// 组合编码后的数据
        double[] encodedData = new double[encodedData1.length + encodedData2.length];
        System.arraycopy(encodedData1, 0, encodedData, 0, encodedData1.length);
        System.arraycopy(encodedData2, 0, encodedData, encodedData1.length, encodedData2.length);
        return encodedData;
    }

    // 归一化处理数值型特征
    public static double[] normalizeFeatures(double[] featureValues) {
        double min = Arrays.stream(featureValues).min().orElse(0.0);
        double max = Arrays.stream(featureValues).max().orElse(1.0);

        double[] normalizedFeatures = new double[featureValues.length];
        for (int i = 0; i < featureValues.length; i++) {
            normalizedFeatures[i] = (featureValues[i] - min) / (max - min);
        }
        return normalizedFeatures;
    }

    // 合并独热编码特征和数值型特征
    private static double[] combineFeatures(double[] encodedData, double[] normalizedFeatures) {
        double[] combinedFeatures = new double[encodedData.length + normalizedFeatures.length];
        System.arraycopy(encodedData, 0, combinedFeatures, 0, encodedData.length);
        System.arraycopy(normalizedFeatures, 0, combinedFeatures, encodedData.length, normalizedFeatures.length);
        return combinedFeatures;
    }

}
