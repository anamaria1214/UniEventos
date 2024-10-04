db.cupones.insertManny([
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
    }
]);

db.cuentas.insertMany([

]);

db.eventos.insertMany([

])