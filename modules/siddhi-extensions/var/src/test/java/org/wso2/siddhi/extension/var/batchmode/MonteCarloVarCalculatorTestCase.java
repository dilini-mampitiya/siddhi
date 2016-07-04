package org.wso2.siddhi.extension.var.batchmode;

import org.junit.Test;
import org.wso2.siddhi.extension.var.models.Asset;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by flash on 7/1/16.
 */
public class MonteCarloVarCalculatorTestCase {

    @Test
    public void testAddEvent() throws Exception {

    }

    @Test
    public void testRemoveEvent() throws Exception {

    }

    @Test
    public void testProcessData() throws Exception {
        int limit=250,calculationsPerDay=100,numberOfTrials=20;
        double historicValues_1 []={
                280.260481,
                287.900505,
                288.000487,
                277.27049,
                266.00045,
                259.200448,
                260.810436,
                256.000443,
                255.450446,
                241.61041,
                239.180418,
                239.160415,
                233.130395,
                231.050389,
                229.240389,
                228.720395,
                231.290391,
                227.800393,
                226.020382,
                228.020392,
                226.980389,
                228.500396,
                226.19039,
                222.290382,
                220.000379,
                219.450381,
                219.78038,
                218.750378,
                223.530382,
                215.810363,
                204.220356,
                198.100339,
                191.400336,
                186.970318,
                185.000312,
                191.450327,
                192.930328,
                193.96033,
                193.230338,
                192.050332,
                193.760334,
                189.220331,
                188.57032,
                185.29032,
                180.040314,
                180.510316,
                180.450308,
                179.570312,
                181.420317,
                179.250314,
                178.980308,
                178.600303,
                180.880305,
                180.040314,
                179.290304,
                175.600295,
                178.610304,
                174.990304,
                177.800302,
                179.980306,
                181.350307,
                185.200323,
                188.810322,
                185.900326,
                187.010323,
                185.180321,
                186.060317,
                187.990318,
                185.870322,
                188.890317,
                193.950329,
                191.370332,
                197.950334,
                197.900343,
                198.410335,
                195.230333,
                192.990336,
                187.400315,
                187.980317,
                191.580329,
                198.640336,
                196.030335,
                204.360344,
                210.860367,
                205.960346,
                191.900327,
                195.62034,
                190.34033,
                188.080315,
                189.240319,
                177.120301,
                180.720314,
                188.280327,
                193.92034,
                197.300338,
                203.900343,
                199.970347,
                195.330332,
                195.380338,
                193.540334,
                195.060341,
                193.85033,
                188.550317,
                193.51033,
                194.500326,
                202.710351,
                192.790325,
                197.600333,
                192.900324,
                192.760336,
                191.910328,
                187.900321,
                186.300319,
                183.750311,
                185.020314,
                180.080304,
                176.470305,
                179.780309,
                178.6903,
                170.450287,
                171.650296,
                173.430292,
                169.980284,
                171.430297,
                176.290296,
                180.400317,
                179.400304,
                179.960303,
                181.980316,
                181.050313,
                179.390302,
                174.760304,
                167.520288,
                165.100282,
                169.400298,
                167.540291,
                172.500289,
                172.540294,
                184.870325,
                182.000319,
                183.020319,
                167.860288,
                168.700295,
                172.550295,
                169.350291,
                184.700317,
                191.670326,
                194.870331,
                196.030335,
                190.640325,
                193.300332,
                185.97032,
                181.800307,
                187.400315,
                172.430294,
                149.380252,
                140.490243,
                147.940256,
                149.160253,
                144.110243,
                142.000249,
                140.900238,
                137.400238,
                135.260224,
                137.730237,
                138.850236,
                137.080241,
                138.370232,
                135.060228,
                132.580229,
                129.600224,
                131.080225,
                126.860221,
                118.260205,
                119.830203,
                120.820207,
                118.380206,
                117.840202,
                119.360208,
                117.4902,
                113.970198,
                112.000192,
                111.490192,
                107.500188,
                105.330177,
                102.310174,
                102.300173,
                101.580175,
                100.010169,
                101.510173,
                100.250171,
                102.370175,
                102.010172,
                106.150181,
                107.910182,
                106.000184,
                104.870176,
                109.400185,
                108.310183,
                100.340176,
        };
        double set_2 []={
                339.920594,
                348.190596,
                339.790592,
                338.770576,
                344.500601,
                351.160615,
                337.060574,
                337.500573,
                343.000582,
                353.880615,
                364.450622,
                368.100641,
                378.180658,
                376.450654,
                364.800624,
                362.620635,
                390.380656,
                377.400645,
                378.070659,
                365.49064,
                366.590636,
                368.750622,
                366.460634,
                342.38059,
                343.320595,
                345.70058,
                362.610618,
                358.770619,
                369.080621,
                367.920617,
                385.100646,
                381.55064,
                396.040687,
                401.780698,
                432.660744,
                426.820719,
                433.490749,
                434.270732,
                433.000729,
                443.030754,
                427.500719,
                399.460691,
                436.450752,
                444.910764,
                467.110798,
                466.250804,
                463.630786,
                471.630797,
                469.760804,
                466.900815,
                465.660801,
                451.240778,
                445.240762,
                435.23074,
                414.860723,
                420.15072,
                426.690717,
                424.64073,
                430.930739,
                432.040752,
                426.330729,
                429.740747,
                424.600725,
                430.150726,
                422.550738,
                418.960727,
                417.490727,
                412.61071,
                409.200692,
                410.650689,
                404.220692,
                404.540705,
                405.850683,
                417.70071,
                414.090696,
                404.910709,
                403.540692,
                423.480726,
                428.620748,
                422.860734,
                416.470712,
                409.360714,
                400.210685,
                403.450695,
                398.150682,
                392.800677,
                396.970675,
                390.400659,
                391.100662,
                379.150652,
                389.900683,
                395.030688,
                390.430678,
                385.950654,
                379.680647,
                379.380637,
                372.140637,
                358.170599,
                353.060596,
                355.440612,
                346.910606,
                348.650597,
                339.900591,
                303.200524,
                308.700533,
                303.280534,
                305.000522,
                296.140517,
                297.44051,
                300.970513,
                306.100518,
                310.650537,
                312.990547,
                312.750545,
                310.710545,
                311.000538,
                318.680536,
                316.460542,
                309.620535,
                306.000535,
                313.940538,
                314.280553,
                315.360546,
                311.370542,
                311.900537,
                307.910533,
                303.790527,
                300.200516,
                302.620506,
                303.000527,
                311.680538,
                309.740521,
                299.090503,
                295.390492,
                294.870514,
                287.110505,
                288.450502,
                286.25048,
                286.000492,
                287.270496,
                288.450502,
                283.580471,
                282.590475,
                282.570472,
                279.580481,
                274.010477,
                280.000476,
                279.99049,
                285.100493,
                285.650491,
                284.000497,
                289.720506,
                284.050489,
                285.68048,
                291.570496,
                291.250514,
                292.35051,
                297.730503,
                297.300521,
                299.190517,
                291.610501,
                287.760486,
                293.500497,
                296.930517,
                296.090495,
                295.850493,
                302.400507,
                313.940538,
                312.00052,
                309.900542,
                299.540518,
                301.190512,
                300.890502,
                298.860518,
                291.780509,
                293.350492,
                296.230514,
                295.540497,
                291.520505,
                295.710505,
                291.250514,
                294.150508,
                292.720514,
                302.000514,
                304.100523,
                297.250499,
                289.710489,
                289.30051,
                287.840496,
                286.700495,
                280.300486,
                277.440467,
                274.800477,
                278.350483,
                282.750497,
                282.500478,
                286.310488,
                279.560478,
                293.120507,
                290.940487,

        };
        double ci=0.95,timeSlice=0.01;
        Map<String, Asset> assets=new HashMap<>();
        Asset asset_1=new Asset(200);
        Asset asset_2=new Asset(250);
//        Asset asset_3=new Asset(150);
        for (int i = 0; i < 200; i++) {
//            asset_1.addHistoricalValue(new Random().nextInt(4)+20+new Random().nextDouble());
            asset_1.addHistoricalValue(historicValues_1[i]);
        }

        assets.put("GOOGL", asset_1);
        for (int i = 0; i < 200; i++) {
//            asset_2.addHistoricalValue(new Random().nextInt(8) + 30 + new Random().nextDouble());
            asset_2.addHistoricalValue(set_2[i]);
        }
        assets.put("APPL", asset_2);
//        for (int i = 0; i < 250; i++) {
//            asset_3.addHistoricalValue(new Random().nextInt(6) + 20 + new Random().nextDouble());
//        }
//        assets.put("APPL", asset_3);
        MonteCarloVarCalculator calc = new MonteCarloVarCalculator(limit,ci,assets,numberOfTrials,calculationsPerDay,timeSlice);
        System.out.println(calc.processData());
    }
}