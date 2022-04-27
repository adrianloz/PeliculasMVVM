package com.smartsoft.peliculasmvvm.utils

object RespuestaWS{

    /*Redirecciones*/
    const val MOVED_PERMANENTLY =301
    /*La página solicitada se ha movido permanentemente a una nueva URI.*/
    const val FOUND = 302

    const val FORBIDDEN = 403
    /*El código de estado HTTP 403  indica al cliente que los datos solicitados están protegidos y, por ende, se le ha denegado el acceso debido a la falta de autorización del cliente..*/
    const val NOT_FOUND = 404

    /* Errores del servidor*/
    const val INTERNAL_SERVER_ERROR = 500
    /*El servidor o no reconoce el método del request o carece de la capacidad para completarlo. Normalmente es algo que se ofrecerá en el futuro, como un nuevo servicio de una API.*/
    const val SERVICE_UNAVAILABLE = 503

    const val NOT_CONTENT_USER = 204

    const val RESPONSE_MENSSAGE_NO_FOUND = "Recurso no disponible"
    const val RESPONSE_MENSSAGE_MOVED_PERMANENTLY = "El recurso solicitado a sido movido permantemente"
    const val RESPONSE_MENSSAGE_FOUND = "El recurso solicitado a sido movido temporalmente"
    const val RESPONSE_MENSSAGE_FORBIDDEN = "Acceso denegado a los datos solicitados"
    const val RESPONSE_MENSSAGE_INTERNAL = "Error inesperado intente mas tarde"
    const val RESPONSE_MENSSAGE_SERVICE = "El servidor no se encuentra disponible"
    const val RESPONSE_MENSSAGE = "Ocurrió un error inesperado intentelo mas tarde"

}
