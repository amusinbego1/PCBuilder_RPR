package ba.unsa.etf.rpr.dal;

public class PCDaoStrategy {
    private static PCDao pcDao = null;

    public static void setPCDao(PCDao pcd){
        pcDao = pcd;
    }

    public static PCDao getPcDao(){
        return pcDao;
    }


}
