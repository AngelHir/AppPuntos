package apppuntos

import com.clases.app.AppService

class BootStrap {

    AppService appService

    def init = { servletContext ->
        appService.crearProductos()
    }
    def destroy = {
    }
}
