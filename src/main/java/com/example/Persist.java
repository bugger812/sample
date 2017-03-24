package com.example;

import com.example.Objects.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.example.Definitions.USER_TYPE_ADMIN;
import static com.example.Definitions.USER_TYPE_TEACHER;

/**
 * Created by Sigal on 5/20/2016.
 */
@Component
public class Persist {

    public Persist() {

    }

    private SessionFactory sessionFactory;

    //@Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public Session getQuerySession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object o) {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public Object load(Class clazz, int id) {
        return getQuerySession().get(clazz, id);
    }

    public Object load(Class clazz, long id) {
        return getQuerySession().get(clazz, id);
    }


    public UserObject getUserByEmailAndPassword(String email, String passowrd) {
        StringBuilder query = new StringBuilder();
        query.append("FROM UserObject WHERE userName=");
        query.append("'").append(email).append("'");
        query.append(" AND password=");
        query.append("'").append(passowrd).append("'");
        return (UserObject) getQuerySession().createQuery(query.toString()).uniqueResult();
    }

    public List<SubjectObject> getAllSubjects() {
        return (List<SubjectObject>) getQuerySession().createQuery("FROM SubjectObject").list();
    }

    public List<UserScoreObject> getTopThree() {
        return (List<UserScoreObject>) getQuerySession().createQuery("SELECT u FROM UserScoreObject u ORDER BY score DESC").setMaxResults(3).list();
    }

    public List<TutorObject> getAvailableTutors() {
        return (List<TutorObject>) getQuerySession().createQuery("FROM TutorObject WHERE active_payment=1").list();
    }

    public List<UserQuestionObject> getUserQuestionsByUid(int uid) {
        StringBuilder query = new StringBuilder("FROM UserQuestionObject ");
        query.append(" WHERE uid=");
        query.append(uid);
        return (List<UserQuestionObject>) getQuerySession().createQuery(query.toString()).list();
    }

    public List<SubjectObject> getAllSubjectsOrdered() {
        return (List<SubjectObject>) getQuerySession().createQuery("FROM SubjectObject ORDER BY priority ASC").list();
    }

    public ExampleObject getNameObject() {
        return (ExampleObject) getQuerySession().createQuery("FROM NameObject ORDER BY RAND()").setMaxResults(1).uniqueResult();
    }

    public List<ExampleObject> getMultipleNameObjects(int amount) {
        return (List<ExampleObject>) getQuerySession().createQuery("FROM NameObject ORDER BY RAND()").setMaxResults(amount).list();
    }

    public List<ExampleObject> getAllNameObjects() {
        return (List<ExampleObject>) getQuerySession().createQuery("FROM NameObject").list();
    }

    public List<UserQuestionObject> getUserQuestionObjectsBySubject(int subjectOid, int uid) {
        StringBuilder query = new StringBuilder("FROM UserQuestionObject ");
        query.append(" WHERE subject.oid=");
        query.append(subjectOid);
        query.append(" AND uid=");
        query.append(uid);
        return (List<UserQuestionObject>) getQuerySession().createQuery(query.toString()).list();
    }

    //   public List<TutorObject> addTutor() {
    //     return (List<TutorObject>) getQuerySession().createQuery("INSERT INTO tutors").list();
//    }

    public long countUsers() {
        return (long) getQuerySession().createQuery("SELECT COUNT(*) FROM UserObject").uniqueResult();
    }

    public long notUplodedExplanationVideo() {
        return (long) getQuerySession().createQuery("SELECT COUNT(*) FROM ExplanationVideoObject WHERE uploaded=FALSE").uniqueResult();
    }


    public List<ExplanationVideoObject> getNotUplodedExplanationVideo() {
        return (List<ExplanationVideoObject>) getQuerySession().createQuery("FROM ExplanationVideoObject WHERE uploaded=FALSE ORDER BY upload_time DESC").list();
    }

    public List<ConfigObject> getConfig() {
        return (List<ConfigObject>) getQuerySession().createQuery("FROM ConfigObject").list();
    }

    public List<UserObject> getMonitoredUsers(int uid) {
        StringBuilder query = new StringBuilder("FROM UserObject WHERE supervisor.uid=");
        query.append(uid);
        return (List<UserObject>) getQuerySession().createQuery(query.toString()).list();
    }

    public List<UserObject> getAllRegularUsers() {
        return (List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE userType!=" + USER_TYPE_ADMIN).list();
    }


    //public List<UserObject> getAllStudents(){
    //    return(List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE ")
    // }

    public UserObject changeSupervisor(int uid, int supervisor) {
        return (UserObject) getQuerySession().createQuery("UPDATE UserObject SET supervisor=" + supervisor + "WHERE uid=" + uid).uniqueResult();
    }


    public List<UserQuestionObject> getUserQuestionObjectByUid(int uid) {
        return (List<UserQuestionObject>) getQuerySession().createQuery("FROM UserQuestionObject WHERE uid=" + uid);//list??
    }

    public List<UserObject> getAllSupervisors() {
        return (List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE userType IN(" + USER_TYPE_ADMIN + "," + USER_TYPE_TEACHER + ")").list();
    }

    public List<UserObject> getAllPendingUsers() {
        return (List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE active=FALSE").list();
    }

    public List<SentMessageObject> getUnreadMessages() {
        return (List<SentMessageObject>) getQuerySession().createQuery("FROM SentMessageObject WHERE messageRead=FALSE").list();
    }

    public UserSubjectLevelObject getSubjectLevel(int uid, int subjectOid) {
        StringBuilder query = new StringBuilder("FROM UserSubjectLevelObject ");
        query.append(" WHERE user.uid=");
        query.append(uid);
        query.append(" AND subject.oid=");
        query.append(subjectOid);
        return (UserSubjectLevelObject) getQuerySession().createQuery(query.toString()).uniqueResult();
    }

    public List<HishgadPackageObject> getAllHishgadPackages() {
        return (List<HishgadPackageObject>) getQuerySession().createQuery("FROM HishgadPackageObject ORDER BY name ASC").list();
    }

    public List<OrderObject> getAllOrders() {
        return (List<OrderObject>) getQuerySession().createQuery("FROM OrderObject ORDER BY oid DESC").list();
    }

    public HishgadDailySalesObject getHishgadDailySalesObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM HishgadDailySalesObject WHERE date = :date");
        query.setDate("date", date);
        return (HishgadDailySalesObject) query.uniqueResult();
    }

    public List<ClientObject> getAllClients() {
        return (List<ClientObject>) getQuerySession().createQuery("FROM ClientObject WHERE active=TRUE ORDER BY name ASC").list();
    }

    public List<DailyDebtObject> getDailyDebtObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailyDebtObject WHERE date = :date");
        query.setDate("date", date);
        return (List<DailyDebtObject>) query.list();
    }

    public List<DailyDebtObject> getDailyDebtObjectsByDateAndClientOid(Date date, int clientOid) {
        Query query = getQuerySession().createQuery("FROM DailyDebtObject WHERE date = :date AND clientObject.oid = :clientOid");
        query.setDate("date", date);
        query.setInteger("clientOid", clientOid);
        return (List<DailyDebtObject>) query.list();
    }


    public DepositObject getDepositObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DepositObject WHERE date = :date");
        query.setDate("date", date);
        return (DepositObject) query.uniqueResult();
    }

    public List<DepositObject> getAllDeposits() {
        return (List<DepositObject>) getQuerySession().createQuery("FROM DepositObject ORDER BY oid DESC").list();
    }

    public IncomeOutcomeObject getIncomeOutcomeObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM IncomeOutcomeObject WHERE date = :date");
        query.setDate("date", date);
        return (IncomeOutcomeObject) query.uniqueResult();
    }

    public List<IncomeOutcomeObject> getAllIncomeOutcomeObjects() {
        return (List<IncomeOutcomeObject>) getQuerySession().createQuery("FROM IncomeOutcomeObject ORDER BY oid DESC").list();
    }

    public DailySalaryObject getDailySalaryByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailySalaryObject WHERE date = :date");
        query.setDate("date", date);
        return (DailySalaryObject) query.uniqueResult();
    }

    public List<DailySalaryObject> getDailySalaryByRange(Date begin, Date end) {
        Query query = getQuerySession().createQuery("FROM DailySalaryObject WHERE date >= :begin AND date < :end");
        query.setDate("begin", begin);
        query.setDate("end", end);
        return (List<DailySalaryObject>) query.list();
    }

    public CreditObject getCreditObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CreditObject WHERE date = :date");
        query.setDate("date", date);
        return (CreditObject) query.uniqueResult();
    }


    public List<CreditObject> getCreditObjects() {
        Query query = getQuerySession().createQuery("FROM CreditObject ORDER BY date DESC");
        return (List<CreditObject>) query.list();
    }


    public CreditRefundObject getCreditRefundObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CreditRefundObject WHERE date = :date");
        query.setDate("date", date);
        return (CreditRefundObject) query.uniqueResult();
    }


    public List<CreditRefundObject> getCreditRefundObjects() {
        Query query = getQuerySession().createQuery("FROM CreditRefundObject ORDER BY date DESC");
        return (List<CreditRefundObject>) query.list();
    }

    public DailySummaryObject getDailySummaryObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailySummaryObject WHERE date = :date");
        query.setDate("date", date);
        return (DailySummaryObject) query.uniqueResult();
    }

    public DailySalesObject getDailySalesObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailySalesObject WHERE date = :date");
        query.setDate("date", date);
        return (DailySalesObject) query.uniqueResult();
    }

    public List<TranslationObject> getAllTranslations() {
        Query query = getQuerySession().createQuery("FROM TranslationObject");
        return (List<TranslationObject>) query.list();
    }

    public List<OrderPackageObject> getAllOrderPackageObject() {
        Query query = getQuerySession().createQuery("FROM OrderPackageObject");
        return (List<OrderPackageObject>) query.list();
    }

    public List<DailyDebtObject> getAllDailyDebtObjets() {
        Query query = getQuerySession().createQuery("FROM DailyDebtObject ORDER BY date DESC");
        return (List<DailyDebtObject>) query.list();
    }

    public List<DailyDebtObject> getDailyDebtObjectByClientOid(int clientOid) {
        Query query = getQuerySession().createQuery("FROM DailyDebtObject WHERE clientObject.oid = :clientOid ORDER BY date ASC");
        query.setInteger("clientOid", clientOid);
        return (List<DailyDebtObject>) query.list();
    }

    public List<DailySalesObject> getAllDailySalesObjects() {
        Query query = getQuerySession().createQuery("FROM DailySalesObject ORDER BY date DESC");
        return (List<DailySalesObject>) query.list();
    }

    public List<HishgadDailySalesObject> getAllHishgadDailySalesObjects() {
        Query query = getQuerySession().createQuery("FROM HishgadDailySalesObject");
        return (List<HishgadDailySalesObject>) query.list();
    }


    public List<CreditRefundObject> getCreditRefundObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CreditRefundObject WHERE date < :date AND lottoPaymentObject IS NULL");
        query.setDate("date", date);
        return (List<CreditRefundObject>) query.list();
    }

    public List<DailySalesObject> getLottoDailySalesObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailySalesObject WHERE date < :date AND lottoPaymentObject IS NULL");
        query.setDate("date", date);
        return (List<DailySalesObject>) query.list();
    }

    public List<OrderObject> getOrderObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM OrderObject WHERE purchaseDate < :date AND lottoPaymentObject IS NULL");
        query.setDate("date", date);
        return (List<OrderObject>) query.list();
    }

    public LottoPaymentObject getLottoPaymentObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM LottoPaymentObject WHERE date = :date");
        query.setDate("date", date);
        return (LottoPaymentObject) query.uniqueResult();
    }

    public List<CreditRefundObject> getCreditRefundObjectsByDate(Date date, int lottoPaymentOid) {
        Query query = getQuerySession().createQuery("FROM CreditRefundObject WHERE (date < :date AND lottoPaymentObject IS NULL) OR lottoPaymentObject.oid = :oid");
        query.setDate("date", date);
        query.setInteger("oid", lottoPaymentOid);
        return (List<CreditRefundObject>) query.list();
    }

    public List<DailySalesObject> getLottoDailySalesObjectsByDate(Date date, int lottoPaymentOid) {
        Query query = getQuerySession().createQuery("FROM DailySalesObject WHERE (date < :date AND lottoPaymentObject IS NULL) OR lottoPaymentObject.oid = :oid");
        query.setDate("date", date);
        query.setInteger("oid", lottoPaymentOid);
        return (List<DailySalesObject>) query.list();
    }

    public List<OrderObject> getOrderObjectsByDate(Date date, int lottoPaymentOid) {
        Query query = getQuerySession().createQuery("FROM OrderObject WHERE (purchaseDate < :date AND lottoPaymentObject IS NULL) OR lottoPaymentObject.oid = :oid");
        query.setDate("date", date);
        query.setInteger("oid", lottoPaymentOid);
        return (List<OrderObject>) query.list();
    }

    public List<LottoPaymentObject> getAllLottoPaymentObjects() {
        return (List<LottoPaymentObject>) getQuerySession().createQuery("FROM LottoPaymentObject").list();
    }

    public List<CreditObject> getAllCreditObjects() {
        return (List<CreditObject>) getQuerySession().createQuery("FROM CreditObject").list();
    }

    public List<CreditObject> getCreditObjectsByDate(Date date, int lottoPaymentOid) {
        Query query = getQuerySession().createQuery("FROM CreditObject WHERE (date < :date AND lottoPaymentObject IS NULL) OR lottoPaymentObject.oid = :oid");
        query.setDate("date", date);
        query.setInteger("oid", lottoPaymentOid);
        return (List<CreditObject>) query.list();
    }

    public BankIncomeOutcomeObject getBankIncomeOutcomeObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM BankIncomeOutcomeObject WHERE date = :date");
        query.setDate("date", date);
        return (BankIncomeOutcomeObject) query.uniqueResult();
    }

    public List<BankIncomeOutcomeObject> getAllBankIncomeOutcomeObjects() {
        return (List<BankIncomeOutcomeObject>) getQuerySession().createQuery("FROM BankIncomeOutcomeObject ORDER BY oid DESC").list();
    }

    public List<DepositObject> getAllDepositObjects() {
        return (List<DepositObject>) getQuerySession().createQuery("FROM DepositObject").list();
    }

    public List<CreditRefundObject> getAllCreditRefundObjects() {
        return (List<CreditRefundObject>) getQuerySession().createQuery("FROM CreditRefundObject").list();
    }

    public List<HishgadPackageObject> getAllNonEmptyHishgadPackages() {
        return (List<HishgadPackageObject>) getQuerySession().createQuery("FROM HishgadPackageObject WHERE remaining > 0 ORDER BY name ASC").list();
    }

    public List<ClientObject> getAllActiveClients() {
        return (List<ClientObject>) getQuerySession().createQuery("FROM ClientObject WHERE active=TRUE ORDER BY name ASC").list();
    }

    public List<HishgadDailySalesObject> getHishgadDailySalesFromDateToNow(Date date) {
        Query query = getQuerySession().createQuery("FROM HishgadDailySalesObject WHERE date > :date");
        query.setDate("date", date);
        return (List<HishgadDailySalesObject>) query.list();
    }

    public OrderObject getOrderObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM OrderObject WHERE purchaseDate = :date");
        query.setDate("date", date);
        return (OrderObject) query.uniqueResult();
    }

    public List<OrderPackageObject> getOrderPackageObjectsOrderOid(int orderOid) {
        Query query = getQuerySession().createQuery("FROM OrderPackageObject WHERE orderObject.oid = :orderOid");
        query.setInteger("orderOid", orderOid);
        return (List<OrderPackageObject>) query.list();
    }

    public OrderPackageObject getOrderPackageObjectByOrderOidAndPackageOid(int orderOid, int packageOid) {
        Query query = getQuerySession().createQuery("FROM OrderPackageObject WHERE orderObject.oid = :orderOid AND hishgadPackageObject.oid = :packageOid");
        query.setInteger("orderOid", orderOid);
        query.setInteger("packageOid", packageOid);
        return (OrderPackageObject) query.uniqueResult();
    }

    public CashDetailsObject getCashDetailsObjectByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CashDetailsObject WHERE date = :date");
        query.setDate("date", date);
        return (CashDetailsObject) query.uniqueResult();
    }

    public List<CashDetailsObject> getCashDetailsObjects() {
        Query query = getQuerySession().createQuery("FROM CashDetailsObject ORDER BY date DESC");
        return (List<CashDetailsObject>) query.list();
    }

    public List<ExceptionObject> getUnsentExceptions() {
        Query query = getQuerySession().createQuery("FROM ExceptionObject WHERE emailSent=FALSE ORDER BY oid ASC");
        return (List<ExceptionObject>) query.list();
    }

    public ConfigObject getLatestDebtSavesDateConfig() {
        Query query = getQuerySession().createQuery("FROM ConfigObject WHERE configKey = 'latestDebtSaved'");
        return (ConfigObject) query.uniqueResult();
    }

    public Date getLastDebtTransactionDateByClientOid(int clientOid) {
        Query query = getQuerySession().createQuery("SELECT date FROM DailyDebtObject WHERE clientObject.oid = :clientOid ORDER BY date DESC");
        query.setMaxResults(1);
        query.setInteger("clientOid", clientOid);
        return (Date) query.uniqueResult();

    }

    public List<HishgadPackageObject> getAllActiveHishgadPackages() {
        return (List<HishgadPackageObject>) getQuerySession().createQuery("FROM HishgadPackageObject WHERE active=TRUE ORDER BY name ASC").list();
    }

    public List<Date> getRedundantBankIncomeOutcomeObject() {
        Query query = getQuerySession().createQuery("SELECT date FROM BankIncomeOutcomeObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<BankIncomeOutcomeObject> getRedundantBankIncomeOutcomeObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM BankIncomeOutcomeObject WHERE date= :date");
        query.setDate("date", date);
        return (List<BankIncomeOutcomeObject>) query.list();
    }

    public void deleteRedundantBankIncomeOutcomeObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE BankIncomeOutcomeObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantCashDetailsObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM CashDetailsObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<CashDetailsObject> getRedundantCashDetailsObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CashDetailsObject WHERE date= :date");
        query.setDate("date", date);
        return (List<CashDetailsObject>) query.list();
    }

    public void deleteRedundantCashDetailsObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE CashDetailsObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantCreditObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM CreditObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<CreditObject> getRedundantCreditObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CreditObject WHERE date= :date");
        query.setDate("date", date);
        return (List<CreditObject>) query.list();
    }

    public void deleteRedundantCreditObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE CreditObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantCreditRefundObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM CreditRefundObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<CreditRefundObject> getRedundantCreditRefundObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM CreditRefundObject WHERE date= :date");
        query.setDate("date", date);
        return (List<CreditRefundObject>) query.list();
    }

    public void deleteRedundantCreditRefundObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE CreditRefundObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantDailySalesObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM DailySalesObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<DailySalesObject> getRedundantDailySalesObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailySalesObject WHERE date= :date");
        query.setDate("date", date);
        return (List<DailySalesObject>) query.list();
    }

    public void deleteRedundantDailySalesObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE DailySalesObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantDailySummaryObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM DailySummaryObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<DailySummaryObject> getRedundantDailySummaryObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DailySummaryObject WHERE date= :date");
        query.setDate("date", date);
        return (List<DailySummaryObject>) query.list();
    }

    public void deleteRedundantDailySummaryObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE DailySummaryObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantDepositObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM DepositObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<DepositObject> getRedundantDepositObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM DepositObject WHERE date= :date");
        query.setDate("date", date);
        return (List<DepositObject>) query.list();
    }

    public void deleteRedundantDepositObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE DepositObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantHishgadDailySalesObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM HishgadDailySalesObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<HishgadDailySalesObject> getRedundantHishgadDailySalesObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM HishgadDailySalesObject WHERE date= :date");
        query.setDate("date", date);
        return (List<HishgadDailySalesObject>) query.list();
    }

    public void deleteRedundantHishgadDailySalesObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE HishgadDailySalesObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantIncomeOutcomeObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM IncomeOutcomeObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<IncomeOutcomeObject> getRedundantIncomeOutcomeObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM IncomeOutcomeObject WHERE date= :date");
        query.setDate("date", date);
        return (List<IncomeOutcomeObject>) query.list();
    }

    public void deleteRedundantIncomeOutcomeObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE IncomeOutcomeObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<Date> getRedundantLottoPaymentObjects() {
        Query query = getQuerySession().createQuery("SELECT date FROM LottoPaymentObject b GROUP BY date HAVING COUNT(*) > 1");
        return (List<Date>) query.list();
    }

    public List<LottoPaymentObject> getRedundantLottoPaymentObjectsByDate(Date date) {
        Query query = getQuerySession().createQuery("FROM LottoPaymentObject WHERE date= :date");
        query.setDate("date", date);
        return (List<LottoPaymentObject>) query.list();
    }

    public void deleteRedundantLottoPaymentObjectsByOids(List<Integer> oids) {
        Query query = getQuerySession().createQuery("DELETE LottoPaymentObject WHERE oid in(:oids)");
        query.setParameterList("oids", oids);
        query.executeUpdate();
    }

    public List<DailySalaryObject> getAllDailySalaryObjects() {
        Query query = getQuerySession().createQuery("FROM DailySalaryObject");
        return (List<DailySalaryObject>) query.list();
    }

    public Double getMaxLottoDailySalary() {
        Query query = getQuerySession().createQuery("SELECT MAX(lottoFee) FROM DailySalaryObject");
        return (Double) query.uniqueResult();
    }

    public Double getMostRecentLottoDailySalary() {
        Query query = getQuerySession().createQuery("SELECT lottoFee FROM DailySalaryObject WHERE lottoFee!=0 ORDER BY date DESC");
        query.setMaxResults(1);
        return (Double) query.uniqueResult();
    }

    public boolean findMessageObjectByTypeAndComment(int type, String comment) {
        Query query = getQuerySession().createQuery("FROM MessageObject WHERE type = :type AND comment = :comment");
        query.setInteger("type",type);
        query.setString("comment", comment);
        MessageObject messageObject = (MessageObject) query.uniqueResult();
        return messageObject != null;
    }

    public List<MessageObject> getActiveMessages() {
        Query query = getQuerySession().createQuery("FROM MessageObject WHERE active=TRUE");
        return (List<MessageObject>) query.list();
    }

    public Double getMaxHishgadDailySalary() {
        Query query = getQuerySession().createQuery("SELECT MAX(hishgadFee) FROM DailySalaryObject");
        return (Double) query.uniqueResult();
    }

    public Double getMostRecentHishgadDailySalary() {
        Query query = getQuerySession().createQuery("SELECT hishgadFee FROM DailySalaryObject WHERE hishgadFee!=0 ORDER BY date DESC");
        query.setMaxResults(1);
        return (Double) query.uniqueResult();
    }




}