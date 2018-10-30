package com.joh.rhms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.joh.rhms.domain.model.NotificationD;
import com.joh.rhms.domain.model.NotificationD.NotificationType;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<NotificationD> findAdminNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em.createNativeQuery("SELECT D.FULL_NAME,IFNULL(SUM(\n" + "CASE\n"
				+ "WHEN DISCOUNT_AMOUNT IS NULL THEN TOTAL_PRICE\n" + "ELSE TOTAL_PRICE-TOTAL_PRICE*DISCOUNT_AMOUNT \n"
				+ "END\n" + "),0)  AS TOTAL_PRICE FROM PATIENT_VISITS PV\n"
				+ "RIGHT OUTER JOIN DOCTORS D  ON PV.I_DOCTOR=D.I_DOCTOR AND VISIT_DATE BETWEEN CURDATE()-INTERVAL 1 DAY AND CURDATE()\n"
				+ "GROUP BY D.I_DOCTOR;");

		List<Object[]> totalDoctorsVisit = query.getResultList();

		for (Object[] row : totalDoctorsVisit) {
			double totalPrice = 0;
			if (row[1] != null)
				totalPrice = Double.parseDouble("" + row[1]);

			//
			NotificationD not = new NotificationD();
			not.setTitle("Dr." + row[0]);
			not.setEtc("" + totalPrice);
			not.setMessage("Total today price");

			not.setNotificationType(NotificationType.INFO);

			notificationDs.add(not);
		}

		return notificationDs;

	}

}
