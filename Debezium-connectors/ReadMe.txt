
Debezium-mysql

Deploy mysql Db 

1) oc rsh `oc get pods -l deployment=mysql -o name` mysql -u mysqluser -pmysqlpw inventory
   or
   oc rsh  mysql-7fbdccb647-pcqfp mysql -u mysqluser -pmysqlpw inventory
   
2)SHOW DATABASES;
3)use inventory;
4)SHOW TABLES;
5)update customers set first_name="kakarla" where id=1002;
5)check topic 'mysql.inventory.customers'
 oc exec -it my-cluster-kafka-0 -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092  --topic mysql.inventory.customers --from-beginning 
Sending Debezium signals:
The following example shows a CREATE TABLE command that creates a three-column debezium_signal table:
a)CREATE TABLE debezium_signal (id VARCHAR(42) PRIMARY KEY, type VARCHAR(32) NOT NULL, data VARCHAR(2048) NULL);
create a new table to test the signal mechanism:
b)CREATE TABLE redhat (
  id SERIAL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);


INSERT INTO redhat VALUES (1, 'Test1', 'TEST1', 'test@test.com');
INSERT INTO redhat VALUES (2, 'Test2', 'TEST2', 'test@test.com');
INSERT INTO redhat VALUES (3, 'Test2', 'TEST3', 'test@test.com');
INSERT INTO redhat VALUES (4, 'Test3', 'TEST3', 'test@test.com');

  Trigger Snapshot:
  Once the following message is displayed If you don’t see a command prompt, try pressing enter, you could sent the following message by copy and paste:
c)  oc exec -it my-cluster-kafka-0 -- bin/kafka-console-producer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic mysql.debezium_signal --property parse.key=true --property key.separator=:
>>mysql:{"type":"execute-snapshot","data": {"data-collections": ["inventory.redhat"], "type": "INCREMENTAL"}}

oc exec -it my-cluster-kafka-0 -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092  --topic mysql.inventory.redhat 



   





