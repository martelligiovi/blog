# Documentación de la API

## PublicacionControlador

- `POST /api/publicacion`: Crea una nueva publicación. El cuerpo de la solicitud debe contener un objeto `PublicacionDTO`. Devuelve el objeto `PublicacionDTO` creado.
- `GET /api/publicacion`: Recupera una lista de publicaciones. Puedes proporcionar parámetros de consulta opcionales para la paginación y la ordenación: `pagenumber`, `pagesize`, `sortby`, `sortdir`. Devuelve un objeto `PublicacionRespuestaDTO`.
- `GET /api/publicacion/{id}`: Recupera una publicación por su ID. Devuelve el objeto `PublicacionDTO`.
- `PUT /api/publicacion/{id}`: Actualiza una publicación por su ID. El cuerpo de la solicitud debe contener un objeto `PublicacionDTO`. Devuelve el objeto `PublicacionDTO` actualizado.
- `DELETE /api/publicacion/{id}`: Elimina una publicación por su ID. Devuelve un mensaje de éxito.

## ComentarioControlador

- `POST /api/publicacion/{id}/comentarios`: Crea un nuevo comentario para una publicación específica. El cuerpo de la solicitud debe contener un objeto `ComentarioDTO`. Devuelve el objeto `ComentarioDTO` creado.
- `GET /api/publicacion/{id}/comentarios`: Recupera una lista de comentarios para una publicación específica. Devuelve una lista de objetos `ComentarioDTO`.
- `GET /api/comentarios/{id}`: Recupera un comentario por su ID. Devuelve el objeto `ComentarioDTO`.
- `PUT /api/comentarios/{id}`: Actualiza un comentario por su ID. El cuerpo de la solicitud debe contener un objeto `ComentarioDTO`. Devuelve el objeto `ComentarioDTO` actualizado.
- `DELETE /api/comentarios/{id}`: Elimina un comentario por su ID. Devuelve un mensaje de éxito.
