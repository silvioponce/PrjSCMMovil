package BL;

import android.content.Context;

import DAO.AdminDao;

/**
 * Created by sponce on 28/7/2016.
 */
public class AdminBL {

    AdminDao adminDao = new AdminDao();

    public Boolean getCreateDB(Context context) {
        return adminDao.getCreateDB(context);
    }

    public Boolean isOnline(Context context) {
        return adminDao.isOnline(context);
    }

}
