DROP TABLE IF EXISTS account;
CREATE TABLE transaction_entity ( id UUID PRIMARY KEY, account_id UUID, amount NUMERIC, type char(6), tr_date date);
