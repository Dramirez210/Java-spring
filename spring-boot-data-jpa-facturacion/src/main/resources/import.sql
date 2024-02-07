INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Jose', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Martha', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Andres', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Martin', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Pedro', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Agustin', 'Guerrero', 'tita@gmail.com', '2019-02-27' , '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Luis', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Cesar', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Omar', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Lupita', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Andrea', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Fernanda', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Luis', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Cesar', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Omar', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Lupita', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Andrea', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Fernanda', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Luis', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Cesar', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Omar', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Lupita', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Andrea', 'Luna', 'jLuna@gmail.com', '2020-10-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES ('Fernanda', 'Guerrero', 'tita@gmail.com', '2019-02-27', '');


/* Productos */
INSERT INTO productos (nombre, precio, create_at) VALUES ('Panasonic Pantalla LCD', 25990, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Sony camara digital DSC-W320B', 12500, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('iPhone 14', 15900, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Samsung SmartTV', 9000, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Mica comoda 5 cajones', 12999, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Sony notebook Z110', 8000, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Zapatera 15 cajones', 1800, now());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Escritorio negro', '2000', now());


/*  Facturas */ 

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura equipos de oficina', null, 1, now() );
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura Bicicleta', 'Desarmada', 1, now() );
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (3, 2, 6);
