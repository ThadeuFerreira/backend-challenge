insert into STORE values(10001, 'Casas Bahias', 'Rua das Flores' )
insert into STORE values(10002, 'Casas Americanas', 'Rua das Bromelhas' )
insert into STORE values(10003, 'Casas Submarinas', 'Rua das Aves' )


insert into ORDERS values(10001, 'Travessa Tiradentes 1', sysdate()-1, 'Open')
insert into ORDERS values(10002, 'Travessa Tiradentes 2', sysdate()-2, 'Close')
insert into ORDERS values(10003, 'Travessa Tiradentes 3', sysdate()-3, 'Canceled')
insert into ORDERS values(10004, 'Travessa Tiradentes 4', sysdate()-4, 'Open')

insert into order_item values(10001, 'Roupa', 2, 10.5, 10001)
insert into order_item values(10002, 'Sapato', 2, 22.5, 10001)
insert into order_item values(10003, 'Calca', 2, 22.5, 10003)


insert into payment values(10001, '400.1232.1232', sysdate() + 10, 'Open', 10002)
insert into payment values(10002, '400.1232.1232', sysdate() + 10, 'Open', 10002)
insert into payment values(10003, '400.1232.1232', sysdate() + 10, 'Open', 10001)
insert into payment values(10004, '400.1232.1232', sysdate() + 10, 'Open', 10001)
insert into payment values(10005, '400.1232.1232', sysdate() + 10, 'Open', 10003)
insert into payment values(10006, '400.1232.1232', sysdate() + 10, 'Open', 10003)
