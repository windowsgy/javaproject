package etl.beans;

public class InstallOrderBean implements OrderBean {
    // 客户经理 1
    private String salesman;
    // 当当快 2
    private String efficient;
    // 小区名称 3
    private String residenceCommunity;
    //装机地址 4
    private String address;
    //接入号 5
    private String accessNum;
    //客户名称
    private String clientName;
    //状态 6
    private String status;
    //预约上门时间 7
    private String reservationTime;
    //任务结束时间 8
    private String taskEndTime;
    //受理时间 9
    private String acceptTime;
    //联系人
    private String linkMan;
    //联系电话
    private String linkPhoneNum;
    //产品
    private String product;
    //业务类型
    private String businessType;
    //商品名称
    private String commodityName;
    //标准地址
    private String standAdd;
    //楼道交换机端口
    private String lanPort;
    //EPON端口
    private String eponPort;
    //ONU设备名称及端口
    private String onuPort;
    //分光器端口
    private String odnPort;
    //分光器名称
    private String odnName;
    //退单原因
    private String returnReason;
    //施工岗(人)/部门
    private String executorMan;
    //最后回单操作人
    private String finalOperator;
    //客户分群
    private String clientGroup;
    //备注
    private String remarks;
    //渠道名称
    private String channelName;
    //10000号受理渠道标志
    private String tabFor10000;
    //原安装地址
    private String installAddSource;
    //原标准地址
    private String standAddSource;
    //带宽
    private String bandwidth;
    //首次挂起时间
    private String firstHangUpTime;
    //最后解挂时间
    private String lastRelieveUpTime;
    //解挂历时
    private String relieveUpLongTime;
    //挂起原因
    private String causeUp;
    //订单编号
    private String orderNum;
    //归档类型
    private String fileType;
    //反馈
    private String feedback;
    //上行光衰
    private String upPower;
    //下行光衰
    private String downPower;
    //OLT发送光功率
    private String oltSendPower;
    //OLT接收送光功率
    private String oltReceivePower;
    //终端发送光功率
    private String terminalSendPower;
    //终端接收送光功率
    private String terminalReceivePower;

    //升维字段================================================

    private int count;

    private String timesTamp ;

    private String id;

    private String importTime;

    private String importFile;


    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getEfficient() {
        return efficient;
    }

    public void setEfficient(String efficient) {
        this.efficient = efficient;
    }

    public String getResidenceCommunity() {
        return residenceCommunity;
    }

    public void setResidenceCommunity(String residenceCommunity) {
        this.residenceCommunity = residenceCommunity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(String accessNum) {
        this.accessNum = accessNum;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getStandAdd() {
        return standAdd;
    }

    public void setStandAdd(String standAdd) {
        this.standAdd = standAdd;
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

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getExecutorMan() {
        return executorMan;
    }

    public void setExecutorMan(String executorMan) {
        this.executorMan = executorMan;
    }

    public String getFinalOperator() {
        return finalOperator;
    }

    public void setFinalOperator(String finalOperator) {
        this.finalOperator = finalOperator;
    }

    public String getClientGroup() {
        return clientGroup;
    }

    public void setClientGroup(String clientGroup) {
        this.clientGroup = clientGroup;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTabFor10000() {
        return tabFor10000;
    }

    public void setTabFor10000(String tabFor10000) {
        this.tabFor10000 = tabFor10000;
    }

    public String getInstallAddSource() {
        return installAddSource;
    }

    public void setInstallAddSource(String installAddSource) {
        this.installAddSource = installAddSource;
    }

    public String getStandAddSource() {
        return standAddSource;
    }

    public void setStandAddSource(String standAddSource) {
        this.standAddSource = standAddSource;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getFirstHangUpTime() {
        return firstHangUpTime;
    }

    public void setFirstHangUpTime(String firstHangUpTime) {
        this.firstHangUpTime = firstHangUpTime;
    }

    public String getLastRelieveUpTime() {
        return lastRelieveUpTime;
    }

    public void setLastRelieveUpTime(String lastRelieveUpTime) {
        this.lastRelieveUpTime = lastRelieveUpTime;
    }

    public String getRelieveUpLongTime() {
        return relieveUpLongTime;
    }

    public void setRelieveUpLongTime(String relieveUpLongTime) {
        this.relieveUpLongTime = relieveUpLongTime;
    }

    public String getCauseUp() {
        return causeUp;
    }

    public void setCauseUp(String causeUp) {
        this.causeUp = causeUp;
    }

    @Override
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getUpPower() {
        return upPower;
    }

    public void setUpPower(String upPower) {
        this.upPower = upPower;
    }

    public String getDownPower() {
        return downPower;
    }

    public void setDownPower(String downPower) {
        this.downPower = downPower;
    }

    public String getOltSendPower() {
        return oltSendPower;
    }

    public void setOltSendPower(String oltSendPower) {
        this.oltSendPower = oltSendPower;
    }

    public String getOltReceivePower() {
        return oltReceivePower;
    }

    public void setOltReceivePower(String oltReceivePower) {
        this.oltReceivePower = oltReceivePower;
    }

    public String getTerminalSendPower() {
        return terminalSendPower;
    }

    public void setTerminalSendPower(String terminalSendPower) {
        this.terminalSendPower = terminalSendPower;
    }

    public String getTerminalReceivePower() {
        return terminalReceivePower;
    }

    public void setTerminalReceivePower(String terminalReceivePower) {
        this.terminalReceivePower = terminalReceivePower;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImportTime() {
        return importTime;
    }

    public void setImportTime(String importTime) {
        this.importTime = importTime;
    }

    public String getImportFile() {
        return importFile;
    }

    public void setImportFile(String importFile) {
        this.importFile = importFile;
    }




}
