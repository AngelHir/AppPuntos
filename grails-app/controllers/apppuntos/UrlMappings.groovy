package apppuntos

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')

        post '/empleado/save' (controller: 'empleado', action: 'save')
        post '/persona/save' (controller: 'persona', action: 'save')
        post '/direccion/save' (controller: 'direccion', action: 'save')
        post '/cliente/save' (controller: 'cliente', action: 'save')
        post '/empresa/save' (controller: 'empresa', action: 'save')
        post '/sucursal/save' (controller: 'sucursal', action: 'save')
        post '/caja/save' (controller: 'caja', action: 'save')
        post '/sesion/save' (controller: 'sesion', action: 'save')
        post '/transaccion/save' (controller: 'transaccion', action: 'save')
        post '/cashback/save' (controller: 'cashback', action: 'save')

        put '/empleado/save' (controller: 'empleado', action: 'update')
        put '/persona/save' (controller: 'persona', action: 'update')
        put '/direccion/save' (controller: 'direccion', action: 'update')
        put '/cliente/save' (controller: 'cliente', action: 'update')
        put '/empresa/save' (controller: 'empresa', action: 'update')
        put '/sucursal/save' (controller: 'sucursal', action: 'update')
        put '/caja/save' (controller: 'caja', action: 'update')
        put '/sesion/save' (controller: 'sesion', action: 'update')
        put '/transaccion/save' (controller: 'transaccion', action: 'update')
        put '/cashback/save' (controller: 'cashback', action: 'update')



    }
}
