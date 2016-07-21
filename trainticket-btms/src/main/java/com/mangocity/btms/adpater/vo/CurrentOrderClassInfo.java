package com.mangocity.btms.adpater.vo;

import org.mangocube.corenut.commons.exception.ErrorCode;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 当前预定的航班服仓位信息
 * Date: 12-2-21
 * Time: 下午3:54
 *
 * @since the version
 */
public class CurrentOrderClassInfo implements Serializable {

    public enum CurrentOrderClassInfoError {
        @ErrorCode(comment = "message type can't null")
        PARSE_TIME_ERROR
    }

    /**
     * 航班号
     */
    private String flightNO;
    /**
     * 航空公司代码
     */
    private String airways;
    /**
     * 舱位代码，如W、Y、C等
     */
    private String classNO;
    /**
     * 价格
     */
    private long price;
    /**
     * 起飞时间, HH:mm:ss
     */
    private String depTime;

    /**
     * 起飞日期 格式为yyyy-MM-dd
     *
     * @param flightNO
     */
    private String depDate;

    /**
     * Added by zhangdeng,2013-08-28 11:10
     * 起始机场
     */
    private String departAirport;

    /**
     * Added by zhangdeng,2013-08-28 11:10
     * 抵达机场
     */
    private String arriveAirport;

    /**
     * Added by zhangdeng,2013-08-28 11:14
     * 是否经停
     */
    private String stopover;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public void setFlightNO(String flightNO) {
        this.flightNO = flightNO;
    }

    public void setAirways(String airways) {
        this.airways = airways;
    }

    public void setClassNO(String classNO) {
        this.classNO = classNO;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getFlightNO() {
        return flightNO;
    }

    public String getAirways() {
        return airways;
    }

    public String getClassNO() {
        return classNO;
    }

    public long getPrice() {
        return price;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public Date getDepDateDetailTime() throws ParseException {
        if (depDate == null || depTime == null) return null;
        String depTimeStr = depDate + " " + depTime.substring(0, 2) + ":" + depTime.substring(2);
        return SDF.parse(depTimeStr);

    }

    public String getDepartAirport() {
        return departAirport;
    }

    public void setDepartAirport(String departAirport) {
        this.departAirport = departAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public String getStopover() {
        return stopover;
    }

    public void setStopover(String stopover) {
        this.stopover = stopover;
    }
}
