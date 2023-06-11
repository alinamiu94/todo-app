INSERT INTO APP_USER(ID, NAME) VALUES (1, 'ALINA');


INSERT INTO TASK(ID, NAME, DESCRIPTION,USER_ID,
				DUE_DATE,STATUS,CREATED_AT,FINISHED_AT) VALUES (1, 'WASH THE DISHES', 'DESCRIPTION', 1,
															   to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'TO DO',
																to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'),
																to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'));