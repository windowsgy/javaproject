package etl.beans;

public class HistoryFailureOrderBean implements OrderBean  {

    private	String	orderNum;    //1	单号码
    private	String	status;	//2	工单状态
    private	String	acceptTime;    //3	受理时间
    private	String	network;    //4	本地网
    private	String	businessNum;    //5	业务号码
    private	String	businessType;    //6	专业类型
    private	String	userName;    //7	用户名
    private	String	userLevel;    //8	客户级别
    private	String	address;    //9	用户地址
    private	String	declarePheno;    //10	申告现象
    private	String	declarePhenoDesc;    //11	申告现象描述
    private	String	ordersSource;    //12	工单来源
    private	String	linkMan;    //13	联系人
    private	String	linkPhoneNum;    //14	联系电话
    private	String	remark;    //15	备注
    private	String	firstResponseTime;    //16	首次相应时间
    private	double	firstResponseTimeLong;    //17	首次相应历时
    private	String	firstResponseOverTime;    //18	首次响应是否超时
    private	String	receiptTime;    //19	回单时间
    private	String	receiptCause;    //20	回单原因
    private	String	principal;    //21	工单责任人
    private	String	principalPost;    //22	责任人岗位
    private	double	assessTimeLong;    //23	考核历时
    private	String	assessTimeLimit;    //24	考核时限
    private	String	filedTime;    //25	归档时间
    private	String	filedMan;    //26	归档人
    private	String	preprocessor;    //27	预处理操作人
    private	String	preprocessorContent;    //28	预处理内容
    private	String	replier;    //29	回单操作人
    private	String	replyContent;    //30	回单内容
    private	long	addInfoCount;    //31	追加信息次数
    private	String	addInfoContent;    //32	追加信息内容
    private	long	rebuildCount;    //33	重修次数
    private	String	rebuildContent;    //34	重修内容
    private	String	overTime;    //35	是否超时
    private	String	clientBase;    //36	客户分群
    private	String	warn;    //37	是否预警
    private	String	controlOverTime;    //38	管控是否超时
    private	String	urgeRepair;    //39	是否催修
    private	long	feedbackCount;    //40	反馈次数
    private	String	feedbackContent;    //41	反馈内容
    private	long	urgeRepairCount;    //42	催修次数
    private	String	urgeRepairContent;    //43	催修内容
    private	String	autoReturnVisitResult;    //44	自动回访结果
    private	String	autoReturnVisitStatus;    //45	自动回访状态
    private	String	customerSatisfaction;    //46	最终回访客户满意度
    private	double	repairTimeLong;    //47	自然修障时长
    private	String	speed;    //48	宽带速率
    private	String	accessWay;    //49	接入方式
    private	String	theDay;    //50	当日修标识
    private	String	theDayAgreement;    //51	当日修是否履约

    //升维字段================================================

    private int count;
    private String timesTamp ;
    private String id;
    private String importTime;
    private String importFile;
    private String year;
    private String month;


    @Override
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeclarePheno() {
        return declarePheno;
    }

    public void setDeclarePheno(String declarePheno) {
        this.declarePheno = declarePheno;
    }

    public String getDeclarePhenoDesc() {
        return declarePhenoDesc;
    }

    public void setDeclarePhenoDesc(String declarePhenoDesc) {
        this.declarePhenoDesc = declarePhenoDesc;
    }

    public String getOrdersSource() {
        return ordersSource;
    }

    public void setOrdersSource(String ordersSource) {
        this.ordersSource = ordersSource;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFirstResponseTime() {
        return firstResponseTime;
    }

    public void setFirstResponseTime(String firstResponseTime) {
        this.firstResponseTime = firstResponseTime;
    }

    public double getFirstResponseTimeLong() {
        return firstResponseTimeLong;
    }

    public void setFirstResponseTimeLong(double firstResponseTimeLong) {
        this.firstResponseTimeLong = firstResponseTimeLong;
    }

    public String getFirstResponseOverTime() {
        return firstResponseOverTime;
    }

    public void setFirstResponseOverTime(String firstResponseOverTime) {
        this.firstResponseOverTime = firstResponseOverTime;
    }

    public String getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getReceiptCause() {
        return receiptCause;
    }

    public void setReceiptCause(String receiptCause) {
        this.receiptCause = receiptCause;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalPost() {
        return principalPost;
    }

    public void setPrincipalPost(String principalPost) {
        this.principalPost = principalPost;
    }

    public double getAssessTimeLong() {
        return assessTimeLong;
    }

    public void setAssessTimeLong(double assessTimeLong) {
        this.assessTimeLong = assessTimeLong;
    }

    public String getAssessTimeLimit() {
        return assessTimeLimit;
    }

    public void setAssessTimeLimit(String assessTimeLimit) {
        this.assessTimeLimit = assessTimeLimit;
    }

    public String getFiledTime() {
        return filedTime;
    }

    public void setFiledTime(String filedTime) {
        this.filedTime = filedTime;
    }

    public String getFiledMan() {
        return filedMan;
    }

    public void setFiledMan(String filedMan) {
        this.filedMan = filedMan;
    }

    public String getPreprocessor() {
        return preprocessor;
    }

    public void setPreprocessor(String preprocessor) {
        this.preprocessor = preprocessor;
    }

    public String getPreprocessorContent() {
        return preprocessorContent;
    }

    public void setPreprocessorContent(String preprocessorContent) {
        this.preprocessorContent = preprocessorContent;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public long getAddInfoCount() {
        return addInfoCount;
    }

    public void setAddInfoCount(long addInfoCount) {
        this.addInfoCount = addInfoCount;
    }

    public String getAddInfoContent() {
        return addInfoContent;
    }

    public void setAddInfoContent(String addInfoContent) {
        this.addInfoContent = addInfoContent;
    }

    public long getRebuildCount() {
        return rebuildCount;
    }

    public void setRebuildCount(long rebuildCount) {
        this.rebuildCount = rebuildCount;
    }

    public String getRebuildContent() {
        return rebuildContent;
    }

    public void setRebuildContent(String rebuildContent) {
        this.rebuildContent = rebuildContent;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getClientBase() {
        return clientBase;
    }

    public void setClientBase(String clientBase) {
        this.clientBase = clientBase;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getControlOverTime() {
        return controlOverTime;
    }

    public void setControlOverTime(String controlOverTime) {
        this.controlOverTime = controlOverTime;
    }

    public String getUrgeRepair() {
        return urgeRepair;
    }

    public void setUrgeRepair(String urgeRepair) {
        this.urgeRepair = urgeRepair;
    }

    public long getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(long feedbackCount) {
        this.feedbackCount = feedbackCount;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public long getUrgeRepairCount() {
        return urgeRepairCount;
    }

    public void setUrgeRepairCount(long urgeRepairCount) {
        this.urgeRepairCount = urgeRepairCount;
    }

    public String getUrgeRepairContent() {
        return urgeRepairContent;
    }

    public void setUrgeRepairContent(String urgeRepairContent) {
        this.urgeRepairContent = urgeRepairContent;
    }

    public String getAutoReturnVisitResult() {
        return autoReturnVisitResult;
    }

    public void setAutoReturnVisitResult(String autoReturnVisitResult) {
        this.autoReturnVisitResult = autoReturnVisitResult;
    }

    public String getAutoReturnVisitStatus() {
        return autoReturnVisitStatus;
    }

    public void setAutoReturnVisitStatus(String autoReturnVisitStatus) {
        this.autoReturnVisitStatus = autoReturnVisitStatus;
    }

    public String getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(String customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }

    public double getRepairTimeLong() {
        return repairTimeLong;
    }

    public void setRepairTimeLong(double repairTimeLong) {
        this.repairTimeLong = repairTimeLong;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getAccessWay() {
        return accessWay;
    }

    public void setAccessWay(String accessWay) {
        this.accessWay = accessWay;
    }

    public String getTheDay() {
        return theDay;
    }

    public void setTheDay(String theDay) {
        this.theDay = theDay;
    }

    public String getTheDayAgreement() {
        return theDayAgreement;
    }

    public void setTheDayAgreement(String theDayAgreement) {
        this.theDayAgreement = theDayAgreement;
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
