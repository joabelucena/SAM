package br.com.ttrans.samapp.library;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHandle {

	/**
	 * Retrieves the amount of hours between two dates considering a custom
	 * daily hours.
	 * 
	 * @param d1
	 *            Initial date
	 * @param d2
	 *            Final date
	 * @param dailyHours
	 *            Equipment daily work hours
	 * @return Amount of hours in the period.
	 */
	public static Double diff(Date d1, Date d2, float dailyHours) {

		Calendar aux1 = Calendar.getInstance();
		Calendar aux2 = Calendar.getInstance();

		aux1.setTime(d1);
		aux2.setTime(d2);

		if (d2.after(d1)) {

			long days = TimeUnit.DAYS.convert(d2.getTime() - d1.getTime(), TimeUnit.MILLISECONDS);

			if (days == 0) {
				/*
				 * Same day
				 */

				return ((double) TimeUnit.MINUTES.convert(d2.getTime() - d1.getTime(), TimeUnit.MILLISECONDS)
						/ 60) > dailyHours ? dailyHours
								: ((double) TimeUnit.MINUTES.convert(d2.getTime() - d1.getTime(), TimeUnit.MILLISECONDS)
										/ 60);

			} else if (days >= 1) {
				/*
				 * Tomorrow
				 */
				double diff1, diff2;

				/*
				 * More than 1 day
				 */
				aux1.add(Calendar.DAY_OF_MONTH, 1);
				aux1.set(Calendar.HOUR_OF_DAY, 0);
				aux1.set(Calendar.MINUTE, 0);
				aux1.set(Calendar.SECOND, 0);

				aux2.set(Calendar.HOUR_OF_DAY, 0);
				aux2.set(Calendar.MINUTE, 0);
				aux2.set(Calendar.SECOND, 0);

				diff1 = (double) TimeUnit.MINUTES.convert(aux1.getTimeInMillis() - d1.getTime(), TimeUnit.MILLISECONDS)
						/ 60;
				diff2 = (double) TimeUnit.MINUTES.convert(d2.getTime() - aux2.getTimeInMillis(), TimeUnit.MILLISECONDS)
						/ 60;

				diff1 = diff1 > dailyHours ? dailyHours : diff1;
				diff2 = diff2 > dailyHours ? dailyHours : diff2;

				if (days == 1) {
					return diff1 + diff2;
				} else {
					return ((double) TimeUnit.DAYS.convert(aux2.getTimeInMillis() - aux1.getTimeInMillis(),
							TimeUnit.MILLISECONDS) * dailyHours) + diff1 + diff2;
				}

			} else {

				return 0d;
			}

		} else {
			/*
			 * Previously day
			 */

			return 0d;
		}

	}

}