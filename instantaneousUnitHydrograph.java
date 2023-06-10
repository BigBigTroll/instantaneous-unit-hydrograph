package aaa;

import org.apache.commons.math3.distribution.GammaDistribution;

public class instantaneousUnitHydrograph {

    public double[] IUH(double n, double K) {

        double[] iuh = new double[100];

        double alpha = n;// 形状参数 n
        double beta = K;// 尺度参数 k

        GammaDistribution distribution = new GammaDistribution(alpha, beta);

        for (int i = 0; i < 100; i++) {// 100个时段
            if(i == 0){
                iuh[i] = 0;
            }else {
                iuh[i] = distribution.cumulativeProbability(i+1) - distribution.cumulativeProbability(i);
            }
        }

        return iuh;
    }
    
    public double[] uhConv(double[] iuh, double[] T) {

        double[][] sums = new double[T.length][];
        for (int i = 0; i < T.length; i++) {
            sums[i] = new double[iuh.length];
            for (int j = 0; j < iuh.length; j++) {
                sums[i][j] = T[i] * iuh[j];
            }
        }

        double[] Q = new double[T.length];

        for (int i = 0; i < T.length; i++) {

            for (int j = 0; j < i+1; j++) {
                int k = i - j;
                if (k < sums[j].length) {// 防止超出单位线长度
                    Q[i] = Q[i]+sums[j][k];
                }
            }
        }

//        double[] sum = new double[m];
//        sum[0] = sums[0][0];
//        sum[1] = sums[0][1] + sums[1][0];
//        sum[2] = sums[0][2] + sums[1][1] + sums[2][0];
//        sum[3] = sums[0][3] + sums[1][2] + sums[2][1] + sums[3][0];
//        ...
//        sum[m-1] = sums[0][n-1] + sums[1][n-2] + ... + sums[m][0];
//
//        return sum;
        return Q;

}
