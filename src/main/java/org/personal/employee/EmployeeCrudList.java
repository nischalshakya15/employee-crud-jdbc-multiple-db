package org.personal.employee;

import org.personal.employee.configuration.PropertiesConfiguration;
import org.personal.employee.dao.BaseDao;
import org.personal.employee.view.View;

public class EmployeeCrudList {

    public static void main(String[] args) {
        if (args.length != 0) {
            for (String s : args) {
                String[] arguments = s.split("=");
                if (arguments[0].equalsIgnoreCase("--database")) {
                    BaseDao.CONNECTION_TYPE = arguments[1];
                } else {
                    BaseDao.CONNECTION_TYPE = PropertiesConfiguration.properties.getProperty("DEFAULT.DATABASE");
                }
                break;
            }
        } else {
            BaseDao.CONNECTION_TYPE = PropertiesConfiguration.properties.getProperty("DEFAULT.DATABASE");
        }
        new View();
    }
}
