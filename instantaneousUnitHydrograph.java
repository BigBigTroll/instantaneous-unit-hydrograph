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

}
