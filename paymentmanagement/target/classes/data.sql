INSERT INTO payment (id, amount, creation_date, description, status)
VALUES ('3f2504e0-4f89-11d3-9a0c-0305e82c3301', 150.75, TIMESTAMP '2025-06-06 00:00:00', 'Pagamento ocasional 1', 'ACCEPTED');

INSERT INTO simple_payment (id, person_document_number)
VALUES ('3f2504e0-4f89-11d3-9a0c-0305e82c3301', '12345678901');
