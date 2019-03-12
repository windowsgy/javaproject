package etl.beans;

public class HistoryInstallOrderBean implements OrderBean  {

    private String product;                                //1产品
    private String includeOrderNum;                                //2所属工单
    private String network;                                //3本地网
    private String orderNum;                                //4订单编号
    private String accessNum;                                //5接入号
    private String speed;                                //6宽带速率
    private String salesman;                                //7客户经理
    private double orderDisposeTimeLong;                                //8工单处理时长
    private String area;                                //9区域
    private String clientName;                                //10客户名称
    private String linkMan;                                //11联系人
    private String linkPhoneNum;                                //12联系电话
    private String commodityName;                                //13商品名称
    private String executeType;                                //14施工类型
    private String executeAction;                                //15施工动作
    private String isPrint;                                //16是否打印
    private String remark;                                //17备注
    private String programCtl;                                //18程控功能
    private String status;                                //19状态
    private String priority;                                //20优先级
    private String executor;                                //21施工人
    private String executeDepartment ;                                //22施工部门
    private String upTime;                                //23挂起时间
    private String feedbackTime;                                //24反馈时间
    private String reservationStartTime;                                //25预约开始时间
    private String reservationEndTime;                                //26预约结束时间
    private String reservationWarnTime;                                //27预约预警时间
    private String taskEndTime;                                //28截止时间
    private String acceptTime;                                //29受理时间
    private String channelName;                                //30渠道名称
    private String arriveTime;                                //31到单时间
    private String completedTime;                                //32竣工时间
    private String isOverTime;                                //33是否超时
    private double orderTimeLong;                                //34工单历时
    private String lanPort;                                //35楼道交换机端口
    private String eponPort;                                //36EPON端口
    private String onuPort;                                //37ONU设备名称及端口
    private String odnPort;                                //38分光器端口
    private String odnName;                                //39分光器名称
    private String vindicatePattern;                                //40维护模式
    private String partnerNumber;                                //41合作方编码
    private String partnerName;                                //42合作方名称
    private String partnerId;                                //43合作方标识
    private double executeTimeLong;                                //44自然施工时长
    private String causeUp;                                //45挂起原因
    private String causeFeedback;                                //46反馈原因
    private String theDayDefer;                                //47当日装慢必赔
    private String fileType;                                //48归档模式
    private String theDay;                                //49当日装履约标识
    private String newAddress;                                //50新装机地址
    private String sourceStandAdd;                                //51原标准地址
    private String newStandAdd;                                //52新标准地址
    private String sourceAddress;                                //53原安装地址
    //升维字段================================================
    private int count;
    private String timesTamp ;
    private String id;
    private String importTime;
    private String importFile;
    private String standAddL6;
    private String year;
    private String month;


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getIncludeOrderNum() {
        return includeOrderNum;
    }

    public void setIncludeOrderNum(String includeOrderNum) {
        this.includeOrderNum = includeOrderNum;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(String accessNum) {
        this.accessNum = accessNum;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public double getOrderDisposeTimeLong() {
        return orderDisposeTimeLong;
    }

    public void setOrderDisposeTimeLong(double orderDisposeTimeLong) {
        this.orderDisposeTimeLong = orderDisposeTimeLong;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhoneNum() {
        return linkPhoneNum;
    }

    public void setLinkPhoneNum(String linkPhoneNum) {
        this.linkPhoneNum = linkPhoneNum;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    public String getExecuteAction() {
        return executeAction;
    }

    public void setExecuteAction(String executeAction) {
        this.executeAction = executeAction;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProgramCtl() {
        return programCtl;
    }

    public void setProgramCtl(String programCtl) {
        this.programCtl = programCtl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecuteDepartment() {
        return executeDepartment;
    }

    public void setExecuteDepartment(String executeDepartment) {
        this.executeDepartment = executeDepartment;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getReservationStartTime() {
        return reservationStartTime;
    }

    public void setReservationStartTime(String reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
    }

    public String getReservationEndTime() {
        return reservationEndTime;
    }

    public void setReservationEndTime(String reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    public String getReservationWarnTime() {
        return reservationWarnTime;
    }

    public void setReservationWarnTime(String reservationWarnTime) {
        this.reservationWarnTime = reservationWarnTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    @Override
    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }

    public String getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(String isOverTime) {
        this.isOverTime = isOverTime;
    }

    public double getOrderTimeLong() {
        return orderTimeLong;
    }

    public void setOrderTimeLong(double orderTimeLong) {
        this.orderTimeLong = orderTimeLong;
    }

    public String getLanPort() {
        return lanPort;
    }

    public void setLanPort(String lanPort) {
        this.lanPort = lanPort;
    }

    public String getEponPort() {
        return eponPort;
    }

    public void setEponPort(String eponPort) {
        this.eponPort = eponPort;
    }

    public String getOnuPort() {
        return onuPort;
    }

    public void setOnuPort(String onuPort) {
        this.onuPort = onuPort;
    }

    public String getOdnPort() {
        return odnPort;
    }

    public void setOdnPort(String odnPort) {
        this.odnPort = odnPort;
    }

    public String getOdnName() {
        return odnName;
    }

    public void setOdnName(String odnName) {
        this.odnName = odnName;
    }

    public String getVindicatePattern() {
        return vindicatePattern;
    }

    public void setVindicatePattern(String vindicatePattern) {
        this.vindicatePattern = vindicatePattern;
    }

    public String getPartnerNumber() {
        return partnerNumber;
    }

    public void setPartnerNumber(String partnerNumber) {
        this.partnerNumber = partnerNumber;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public double getExecuteTimeLong() {
        return executeTimeLong;
    }

    public void setExecuteTimeLong(double executeTimeLong) {
        this.executeTimeLong = executeTimeLong;
    }

    public String getCauseUp() {
        return causeUp;
    }

    public void setCauseUp(String causeUp) {
        this.causeUp = causeUp;
    }

    public String getCauseFeedback() {
        return causeFeedback;
    }

    public void setCauseFeedback(String causeFeedback) {
        this.causeFeedback = causeFeedback;
    }

    public String getTheDayDefer() {
        return theDayDefer;
    }

    public void setTheDayDefer(String theDayDefer) {
        this.theDayDefer = theDayDefer;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTheDay() {
        return theDay;
    }

    public void setTheDay(String theDay) {
        this.theDay = theDay;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getSourceStandAdd() {
        return sourceStandAdd;
    }

    public void setSourceStandAdd(String sourceStandAdd) {
        this.sourceStandAdd = sourceStandAdd;
    }

    public String getNewStandAdd() {
        return newStandAdd;
    }

    public void setNewStandAdd(String newStandAdd) {
        this.newStandAdd = newStandAdd;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    @Override
    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getImportTime() {
        return importTime;
    }

    @Override
    public void setImportTime(String importTime) {
        this.importTime = importTime;
    }

    public String getImportFile() {
        return importFile;
    }

    @Override
    public void setImportFile(String importFile) {
        this.importFile = importFile;
    }

    public String getStandAddL6() {
        return standAddL6;
    }

    public void setStandAddL6(String standAddL6) {
        this.standAddL6 = standAddL6;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
