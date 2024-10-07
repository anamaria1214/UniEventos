db.cupones.insertMany([
    {
        "_id": {
            "$oid": "66fad22f9baeae320a4cfb8d"
        },
        "nombre": "Descuento Especial",
        "descuento": 15.5,
        "fechaVencimiento": {
            "$date": "2024-10-24T04:59:59.000Z"
        },
        "codigo": "DESC20",
        "estado": "NO_DISPONIBLE",
        "tipo": "UNICO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cupon"
    },
    {
        "_id": {
            "$oid": "66fdf5e67ff62e1f58ce8616"
        },
        "nombre": "Descuento del 20%",
        "descuento": 10,
        "fechaVencimiento": {
            "$date": "2024-10-24T04:59:59.000Z"
        },
        "codigo": "DESC10",
        "estado": "DISPONIBLE",
        "tipo": "UNICO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cupon"
    },
    {
        "_id": {
            "$oid": "66fe12d4a8b76d2b35f77c29"
        },
        "nombre": "Descuento Premium",
        "descuento": 25,
        "fechaVencimiento": {
            "$date": "2024-11-01T04:59:59.000Z"
        },
        "codigo": "PREM25",
        "estado": "DISPONIBLE",
        "tipo": "MULTIUSO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cupon"
    },
    {
        "_id": {
            "$oid": "66fe13f8b2c76d3b16d47d7b"
        },
        "nombre": "Descuento Flash",
        "descuento": 5,
        "fechaVencimiento": {
            "$date": "2024-10-15T04:59:59.000Z"
        },
        "codigo": "FLASH5",
        "estado": "NO_DISPONIBLE",
        "tipo": "UNICO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cupon"
    },
    {
        "_id": {
            "$oid": "66fe1502e8c93d4a1cf5d842"
        },
        "nombre": "Descuento Frecuente",
        "descuento": 12.5,
        "fechaVencimiento": {
            "$date": "2024-12-31T04:59:59.000Z"
        },
        "codigo": "FREQ12",
        "estado": "DISPONIBLE",
        "tipo": "MULTIUSO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cupon"
    }
]);


db.cuentas.insertMany([
    {
        "_id": {
            "$oid": "6701a8f9baeae320b8bafc01"
        },
        "usuario": {
            "cedula": "123456789",
            "nombre": "Miguel Florez",
            "telefono": "3124567890",
            "direccion": "Calle 10 #15-45"
        },
        "email": "miguel.florez@example.com",
        "password": "password123",
        "codValidacionPassword": {
            "codigo": "1234",
            "fechaCreacion": {
                "$date": "2024-10-15T00:00:00.000Z"
            }
        },
        "codValidacionRegistro": {
            "codigo": "5678",
            "fechaCreacion": {
                "$date": "2024-10-10T00:00:00.000Z"
            }
        },
        "fechaRegistro": {
            "$date": "2024-09-01T08:30:00.000Z"
        },
        "rol": "CLIENTE",
        "estado": "ACTIVO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cuenta"
    },
    {
        "_id": {
            "$oid": "6701a8f9baeae320b8bafc02"
        },
        "usuario": {
            "cedula": "987654321",
            "nombre": "Laura Martinez",
            "telefono": "3109876543",
            "direccion": "Carrera 20 #30-60"
        },
        "email": "laura.martinez@example.com",
        "password": "password456",
        "codValidacionPassword": {
            "codigo": "4321",
            "fechaCreacion": {
                "$date": "2024-10-20T00:00:00.000Z"
            }
        },
        "codValidacionRegistro": {
            "codigo": "8765",
            "fechaCreacion": {
                "$date": "2024-10-05T00:00:00.000Z"
            }
        },
        "fechaRegistro": {
            "$date": "2024-08-20T14:45:00.000Z"
        },
        "rol": "ADMINISTRADOR",
        "estado": "ACTIVO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cuenta"
    },
    {
        "_id": {
            "$oid": "6701a8f9baeae320b8bafc03"
        },
        "usuario": {
            "cedula": "456789123",
            "nombre": "Carlos Lopez",
            "telefono": "3112345678",
            "direccion": "Avenida 15 #5-20"
        },
        "email": "carlos.lopez@example.com",
        "password": "password789",
        "codValidacionPassword": {
            "codigo": "1357",
            "fechaCreacion": {
                "$date": "2024-10-18T00:00:00.000Z"
            }
        },
        "codValidacionRegistro": {
            "codigo": "2468",
            "fechaCreacion": {
                "$date": "2024-10-08T00:00:00.000Z"
            }
        },
        "fechaRegistro": {
            "$date": "2024-09-10T10:00:00.000Z"
        },
        "rol": "CLIENTE",
        "estado": "INACTIVO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cuenta"
    },
    {
        "_id": {
            "$oid": "6701a8f9baeae320b8bafc04"
        },
        "usuario": {
            "cedula": "321654987",
            "nombre": "Andrea Perez",
            "telefono": "3145671234",
            "direccion": "Calle 5 #45-30"
        },
        "email": "andrea.perez@example.com",
        "password": "password321",
        "codValidacionPassword": {
            "codigo": "9753",
            "fechaCreacion": {
                "$date": "2024-10-25T00:00:00.000Z"
            }
        },
        "codValidacionRegistro": {
            "codigo": "8642",
            "fechaCreacion": {
                "$date": "2024-10-12T00:00:00.000Z"
            }
        },
        "fechaRegistro": {
            "$date": "2024-08-15T09:15:00.000Z"
        },
        "rol": "ADMINISTRADOR",
        "estado": "ACTIVO",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cuenta"
    },
    {
        "_id": {
            "$oid": "6701a8f9baeae320b8bafc05"
        },
        "usuario": {
            "cedula": "654123987",
            "nombre": "Juan Gomez",
            "telefono": "3157894321",
            "direccion": "Carrera 7 #12-50"
        },
        "email": "juan.gomez@example.com",
        "password": "password654",
        "codValidacionPassword": {
            "codigo": "2468",
            "fechaCreacion": {
                "$date": "2024-10-19T00:00:00.000Z"
            }
        },
        "codValidacionRegistro": {
            "codigo": "7531",
            "fechaCreacion": {
                "$date": "2024-10-09T00:00:00.000Z"
            }
        },
        "fechaRegistro": {
            "$date": "2024-09-05T11:30:00.000Z"
        },
        "rol": "CLIENTE",
        "estado": "ELIMINADA",
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Cuenta"
    }
]);


db.eventos.insertMany([
    {
        "_id": {
            "$oid": "6702b8e9baeae320c8bafc01"
        },
        "nombre": "Maratón de la Ciudad",
        "descripcion": "Evento deportivo anual con corredores de todo el país.",
        "direccion": "Calle 50 #45-30",
        "ciudad": "Bogotá",
        "fecha": {
            "$date": "2024-11-10T08:00:00.000Z"
        },
        "estado": "ACTIVO",
        "tipo": "DEPORTE",
        "imagenPortada": "maraton_portada.jpg",
        "imagenLocalidades": "maraton_localidades.jpg",
        "localidades": [
            {
                "nombre": "General",
                "precio": 50.0,
                "entradasVendidas": 200,
                "capacidadMaxima": 500
            },
            {
                "nombre": "VIP",
                "precio": 100.0,
                "entradasVendidas": 50,
                "capacidadMaxima": 100
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Evento"
    },
    {
        "_id": {
            "$oid": "6702b8e9baeae320c8bafc02"
        },
        "nombre": "Concierto de Rock",
        "descripcion": "Una noche de rock con las mejores bandas del país.",
        "direccion": "Estadio Nacional",
        "ciudad": "Medellín",
        "fecha": {
            "$date": "2024-12-05T19:00:00.000Z"
        },
        "estado": "ACTIVO",
        "tipo": "CONCIERTO",
        "imagenPortada": "concierto_rock_portada.jpg",
        "imagenLocalidades": "concierto_rock_localidades.jpg",
        "localidades": [
            {
                "nombre": "Platea",
                "precio": 150.0,
                "entradasVendidas": 300,
                "capacidadMaxima": 500
            },
            {
                "nombre": "VIP",
                "precio": 250.0,
                "entradasVendidas": 100,
                "capacidadMaxima": 200
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Evento"
    },
    {
        "_id": {
            "$oid": "6702b8e9baeae320c8bafc03"
        },
        "nombre": "Feria de Arte Contemporáneo",
        "descripcion": "Exposición de arte contemporáneo con artistas nacionales e internacionales.",
        "direccion": "Centro Cultural",
        "ciudad": "Cali",
        "fecha": {
            "$date": "2024-10-20T10:00:00.000Z"
        },
        "estado": "ACTIVO",
        "tipo": "CULTURAL",
        "imagenPortada": "feria_arte_portada.jpg",
        "imagenLocalidades": "feria_arte_localidades.jpg",
        "localidades": [
            {
                "nombre": "General",
                "precio": 30.0,
                "entradasVendidas": 400,
                "capacidadMaxima": 600
            },
            {
                "nombre": "Estudiantes",
                "precio": 15.0,
                "entradasVendidas": 150,
                "capacidadMaxima": 200
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Evento"
    },
    {
        "_id": {
            "$oid": "6702b8e9baeae320c8bafc04"
        },
        "nombre": "Desfile de Moda Primavera",
        "descripcion": "Desfile de moda que muestra las tendencias de la próxima temporada.",
        "direccion": "Centro de Convenciones",
        "ciudad": "Barranquilla",
        "fecha": {
            "$date": "2024-11-15T17:00:00.000Z"
        },
        "estado": "INACTIVO",
        "tipo": "MODA",
        "imagenPortada": "desfile_moda_portada.jpg",
        "imagenLocalidades": "desfile_moda_localidades.jpg",
        "localidades": [
            {
                "nombre": "Front Row",
                "precio": 200.0,
                "entradasVendidas": 50,
                "capacidadMaxima": 80
            },
            {
                "nombre": "General",
                "precio": 80.0,
                "entradasVendidas": 200,
                "capacidadMaxima": 300
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Evento"
    },
    {
        "_id": {
            "$oid": "6702b8e9baeae320c8bafc05"
        },
        "nombre": "Expo Belleza 2024",
        "descripcion": "La mayor exposición de productos y servicios de belleza del país.",
        "direccion": "Plaza Mayor",
        "ciudad": "Cartagena",
        "fecha": {
            "$date": "2024-11-25T09:00:00.000Z"
        },
        "estado": "ACTIVO",
        "tipo": "BELLEZA",
        "imagenPortada": "expo_belleza_portada.jpg",
        "imagenLocalidades": "expo_belleza_localidades.jpg",
        "localidades": [
            {
                "nombre": "General",
                "precio": 40.0,
                "entradasVendidas": 250,
                "capacidadMaxima": 400
            },
            {
                "nombre": "VIP",
                "precio": 100.0,
                "entradasVendidas": 80,
                "capacidadMaxima": 100
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Evento"
    }
]);
db.ordenes.insertMany([
    {
        "_id": {
            "$oid": "6703a8d9baeae320d8bafc01"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc01"
        },
        "idCupon": {
            "$oid": "66fad22f9baeae320a4cfb8d"
        },
        "fecha": {
            "$date": "2024-09-15T10:30:00.000Z"
        },
        "codigoPasarela": "PAY12345",
        "items": [
            {
                "id": {
                    "$oid": "6702b8e9baeae320c8bafc01"
                },
                "idEvento": "6702b8e9baeae320c8bafc01",
                "nombreLocalidad": "General",
                "precio": 50.0,
                "cantidad": 2
            }
        ],
        "pago": {
            "id": "PAY56789",
            "moneda": "COP",
            "tipoPago": "Tarjeta de crédito",
            "detalleEstado": "Pago exitoso",
            "codigoAutorizacion": "AUTH123456",
            "fecha": {
                "$date": "2024-09-15T10:35:00.000Z"
            },
            "valorTransaccion": 100.0,
            "estado": "COMPLETADO"
        },
        "Total": 85.0,
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Orden"
    },
    {
        "_id": {
            "$oid": "6703a8d9baeae320d8bafc02"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc02"
        },
        "idCupon": {
            "$oid": "66fdf5e67ff62e1f58ce8616"
        },
        "fecha": {
            "$date": "2024-09-20T15:00:00.000Z"
        },
        "codigoPasarela": "PAY98765",
        "items": [
            {
                "id": {
                    "$oid": "6702b8e9baeae320c8bafc02"
                },
                "idEvento": "6702b8e9baeae320c8bafc02",
                "nombreLocalidad": "Platea",
                "precio": 150.0,
                "cantidad": 1
            }
        ],
        "pago": {
            "id": "PAY87654",
            "moneda": "USD",
            "tipoPago": "Paypal",
            "detalleEstado": "Pago exitoso",
            "codigoAutorizacion": "AUTH987654",
            "fecha": {
                "$date": "2024-09-20T15:05:00.000Z"
            },
            "valorTransaccion": 150.0,
            "estado": "COMPLETADO"
        },
        "Total": 135.0,
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Orden"
    },
    {
        "_id": {
            "$oid": "6703a8d9baeae320d8bafc03"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc03"
        },
        "idCupon": {
            "$oid": "66fad22f9baeae320a4cfb8d"
        },
        "fecha": {
            "$date": "2024-09-25T12:00:00.000Z"
        },
        "codigoPasarela": "PAY34567",
        "items": [
            {
                "id": {
                    "$oid": "6702b8e9baeae320c8bafc03"
                },
                "idEvento": "6702b8e9baeae320c8bafc03",
                "nombreLocalidad": "General",
                "precio": 30.0,
                "cantidad": 3
            }
        ],
        "pago": {
            "id": "PAY65432",
            "moneda": "COP",
            "tipoPago": "Tarjeta de débito",
            "detalleEstado": "Pago exitoso",
            "codigoAutorizacion": "AUTH345678",
            "fecha": {
                "$date": "2024-09-25T12:05:00.000Z"
            },
            "valorTransaccion": 90.0,
            "estado": "COMPLETADO"
        },
        "Total": 76.5,
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Orden"
    },
    {
        "_id": {
            "$oid": "6703a8d9baeae320d8bafc04"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc04"
        },
        "idCupon": {
            "$oid": "66fad22f9baeae320a4cfb8d"
        },
        "fecha": {
            "$date": "2024-10-01T09:00:00.000Z"
        },
        "codigoPasarela": "PAY56789",
        "items": [
            {
                "id": {
                    "$oid": "6702b8e9baeae320c8bafc04"
                },
                "idEvento": "6702b8e9baeae320c8bafc04",
                "nombreLocalidad": "VIP",
                "precio": 200.0,
                "cantidad": 1
            }
        ],
        "pago": {
            "id": "PAY876543",
            "moneda": "COP",
            "tipoPago": "Tarjeta de crédito",
            "detalleEstado": "Pago exitoso",
            "codigoAutorizacion": "AUTH456789",
            "fecha": {
                "$date": "2024-10-01T09:05:00.000Z"
            },
            "valorTransaccion": 200.0,
            "estado": "COMPLETADO"
        },
        "Total": 170.0,
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Orden"
    },
    {
        "_id": {
            "$oid": "6703a8d9baeae320d8bafc05"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc05"
        },
        "idCupon": {
            "$oid": "66fdf5e67ff62e1f58ce8616"
        },
        "fecha": {
            "$date": "2024-10-05T14:00:00.000Z"
        },
        "codigoPasarela": "PAY09876",
        "items": [
            {
                "id": {
                    "$oid": "6702b8e9baeae320c8bafc05"
                },
                "idEvento": "6702b8e9baeae320c8bafc05",
                "nombreLocalidad": "General",
                "precio": 40.0,
                "cantidad": 2
            }
        ],
        "pago": {
            "id": "PAY543210",
            "moneda": "USD",
            "tipoPago": "Paypal",
            "detalleEstado": "Pago exitoso",
            "codigoAutorizacion": "AUTH654321",
            "fecha": {
                "$date": "2024-10-05T14:05:00.000Z"
            },
            "valorTransaccion": 80.0,
            "estado": "COMPLETADO"
        },
        "Total": 72.0,
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Orden"
    }
]);
db.carritos.insertMany([
    {
        "_id": {
            "$oid": "6704b1e9baeae320d8cafc01"
        },
        "fechaUltimaAct": {
            "$date": "2024-10-05T15:30:00.000Z"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc01"
        },
        "items": [
            {
                "cantidad": 2,
                "nombreLocalidad": "General",
                "idEvento": "6702b8e9baeae320c8bafc01"
            },
            {
                "cantidad": 1,
                "nombreLocalidad": "Platea",
                "idEvento": "6702b8e9baeae320c8bafc02"
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Carrito"
    },
    {
        "_id": {
            "$oid": "6704b1e9baeae320d8cafc02"
        },
        "fechaUltimaAct": {
            "$date": "2024-10-06T09:00:00.000Z"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc02"
        },
        "items": [
            {
                "cantidad": 3,
                "nombreLocalidad": "VIP",
                "idEvento": "6702b8e9baeae320c8bafc03"
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Carrito"
    },
    {
        "_id": {
            "$oid": "6704b1e9baeae320d8cafc03"
        },
        "fechaUltimaAct": {
            "$date": "2024-10-07T18:45:00.000Z"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc03"
        },
        "items": [
            {
                "cantidad": 1,
                "nombreLocalidad": "General",
                "idEvento": "6702b8e9baeae320c8bafc01"
            },
            {
                "cantidad": 2,
                "nombreLocalidad": "Platea",
                "idEvento": "6702b8e9baeae320c8bafc02"
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Carrito"
    },
    {
        "_id": {
            "$oid": "6704b1e9baeae320d8cafc04"
        },
        "fechaUltimaAct": {
            "$date": "2024-10-08T14:30:00.000Z"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc04"
        },
        "items": [
            {
                "cantidad": 1,
                "nombreLocalidad": "General",
                "idEvento": "6702b8e9baeae320c8bafc04"
            },
            {
                "cantidad": 1,
                "nombreLocalidad": "VIP",
                "idEvento": "6702b8e9baeae320c8bafc05"
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Carrito"
    },
    {
        "_id": {
            "$oid": "6704b1e9baeae320d8cafc05"
        },
        "fechaUltimaAct": {
            "$date": "2024-10-09T11:20:00.000Z"
        },
        "idCuenta": {
            "$oid": "6701a8f9baeae320b8bafc05"
        },
        "items": [
            {
                "cantidad": 2,
                "nombreLocalidad": "General",
                "idEvento": "6702b8e9baeae320c8bafc05"
            }
        ],
        "_class": "co.edu.uniquindio.unieventos.modelo.documentos.Carrito"
    }
]);
