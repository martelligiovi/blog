const apiUrl = 'http://localhost:8080/api/publicacion'; // Reemplaza con la URL de tu API

document.addEventListener('DOMContentLoaded', () => {
  const postContent = document.getElementById('post-content');
  const headerTitle = document.querySelector('header h1');

  // Obtener y mostrar la publicación completa y los comentarios
  fetchPostAndComments();

  async function fetchPostAndComments() {
    try {
      const postId = getPostIdFromUrl();
      console.log(getPostIdFromUrl());
      const postResponse = await fetch(`http://localhost:8080/api/publicacion/${postId}`);
      const postData = await postResponse.json();
  
      // Mostrar el título de la publicación en  el header
      document.querySelector('header h1').textContent = postData.titulo;
  
      displayPost(postData);
    } catch (error) {
      console.error('Error al obtener la publicación:', error);
    }
  }

  const inicioButton = document.getElementById('inicioButton');
  inicioButton.addEventListener('click', () => {
    // Redirigir a la página principal (ajusta la URL según sea necesario)
    window.location.href = 'http://192.168.1.66:8080/';
  });

  

  function displayPost(post) {
    // Mostrar la publicación
    const postContent = document.getElementById('post-content');
    const postElement = createPostElement(post);

    // Crear el botón de edición
    const editButton = document.createElement('button');
    editButton.textContent = 'Editar';
    editButton.style.position = 'absolute';
    editButton.style.right = '10px';
    editButton.style.bottom = '10px';


    editButton.addEventListener('click', () => {
      const updatePostPanel = document.getElementById('updatePostForm');
      updatePostPanel.style.display = 'block';
  
      // Llenar los campos del panel con los datos actuales de la publicación
      const data = handleUpdatePost()
      console.log(data);

      const url = `http://localhost:8080/api/publicacion/${post.id}`;

      fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data), // Asegúrate de que este es el formato correcto para tu API
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al editar el comentario');
        }
      })
      .catch(error => console.error('Error:', error));



      


      window.location.href = 'http://192.168.1.66:8080/edit/' + post.id;
  });

    // Agregar el botón de edición al postElement
    postElement.appendChild(editButton);

    postContent.appendChild(postElement);

    // Obtener y mostrar los comentarios
    fetchComments(post.id);
}


function handleUpdatePost() {
  // Obtén los valores de los campos del formulario
  const titleInput = document.getElementById('title-input');
  const contentInput = document.getElementById('content-input');
  const descriptionInput = document.getElementById('description-input');

  // Comprueba si cada campo está vacío
  if (titleInput.value.trim() !== '') {
      // Si el campo de título no está vacío, actualiza el título del post
      post.title = titleInput.value;
  }
  // Haz lo mismo para el contenido del post
  if (contentInput.value.trim() !== '') {
      post.content = contentInput.value;
  }

  data = {
      "titulo": post.title,
      "contenido": post.content,
      "descripcion": post.description
  };

  // Llama a la función que actualiza el post en la base de datos
  return data;

}







  
  function createPostElement(post) {
    const postElement = document.createElement('div');
    postElement.classList.add('post');

    const titleElement = document.createElement('h2');
    titleElement.textContent = post.titulo;

    const descriptionElement = document.createElement('p');
    descriptionElement.textContent = post.descripcion;

    const contentElement = document.createElement('p');
    contentElement.textContent = post.contenido;

    postElement.appendChild(titleElement);
    postElement.appendChild(descriptionElement);
    postElement.appendChild(contentElement);

    return postElement;
  }


  function getPostIdFromUrl() {
    // Obtener el valor del parámetro "id" de la URL
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
  }

  async function fetchComments(postId) {
    try {
      const commentsResponse = await fetch(`http://localhost:8080/api/publicacion/${postId}/comentarios`);
      const commentsData = await commentsResponse.json();
  
      // Mostrar los comentarios
      displayComments(commentsData);
    } catch (error) {
      console.error('Error al obtener los comentarios:', error);
    }
  }

  function displayComments(comments) {
    const commentsElement = document.createElement('div');
    commentsElement.classList.add('comments');
  
    const commentsTitle = document.createElement('h3');
    commentsTitle.textContent = 'Comentarios';
  
    commentsElement.appendChild(commentsTitle);
  
    if (comments.length > 0) {
      comments.forEach(comment => {
        const commentElement = document.createElement('div');
        commentElement.classList.add('comment');
      
        const commentContent = document.createElement('p');
        commentContent.textContent = comment.cuerpo;
      
        const commentUser = document.createElement('p');
        commentUser.textContent = `Usuario: ${comment.nombre || 'Anónimo'}`;
      
        // Crear botones de editar y eliminar
        const editButton = document.createElement('button');
        const deleteButton = document.createElement('button');
      
        // Agregar texto a los botones
        editButton.textContent = 'Editar';
        deleteButton.textContent = 'Eliminar';
      
        // Agregar eventos de clic a los botones
        editButton.addEventListener('click', () => {
          const commentId = comment.id;
          const commentEmail = comment.email;
          const currentContent = comment.cuerpo;

          // Puedes reemplazar esto con un cuadro de diálogo o un formulario más sofisticado
          const newContent = prompt('Ingrese el nuevo contenido del comentario', currentContent);

          const data = {
            "nombre": comment.nombre,
            "email": comment.email,
            "cuerpo": newContent,
            "publicacion": comment.publicacion
          };

          if (newContent === null || newContent === '') {
            // El usuario canceló el cuadro de diálogo o ingresó un contenido vacío
            return;
          }

          const url = `http://localhost:8080/api/comentarios/${commentId}`;

          fetch(url, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data), // Asegúrate de que este es el formato correcto para tu API
          })
          .then(response => {
            if (!response.ok) {
              throw new Error('Error al editar el comentario');
            }
            commentContent.textContent = newContent; // Actualiza el contenido del comentario en el DOM
          })
          .catch(error => console.error('Error:', error));
        });
      
        deleteButton.addEventListener('click', () => {
          deleteComment(comment.id, commentElement);
        });
      
        // Crear un contenedor para los botones
        const buttonContainer = document.createElement('div');
        buttonContainer.classList.add('button-container');
      
        // Agregar los botones al contenedor
        buttonContainer.appendChild(editButton);
        buttonContainer.appendChild(deleteButton);
      
        commentElement.appendChild(commentUser);
        commentElement.appendChild(commentContent);
        // Agregar el contenedor de botones al comentario
        commentElement.appendChild(buttonContainer);
      
        commentsElement.appendChild(commentElement);
      });
    } else {
      const noCommentsMessage = document.createElement('p');
      noCommentsMessage.textContent = 'No hay comentarios aún.';
      commentsElement.appendChild(noCommentsMessage);
    }
  
    // Agregar los comentarios al DOM
    const postContent = document.getElementById('post-content');
    postContent.appendChild(commentsElement);
  }

  const createCommentButton = document.getElementById('createCommentButton');
  const commentFormContainer = document.getElementById('commentFormContainer');
  const commentForm = document.getElementById('commentForm');
  const commentContentInput = document.getElementById('commentContent');
  
  createCommentButton.addEventListener('click', () => {
    // Muestra el formulario cuando se hace clic en "Crear un comentario"
    commentFormContainer.style.display = 'block';
  });
  
  commentForm.addEventListener('submit', (event) => {
    event.preventDefault();
  
    // Aquí puedes enviar el comentario a tu API o realizar las acciones necesarias
    const commentContent = commentContentInput.value;
    sendComment(commentContent); // Envía el contenido del comentario
  
    // Puedes ocultar el formulario después de enviar el comentario si lo deseas
    commentFormContainer.style.display = 'none';
  
    // Aquí puedes agregar lógica para mostrar el comentario en la página si es necesario
  });
  
  function sendComment(cuerpo) {
    const postId = getPostIdFromUrl();
    const url = `http://localhost:8080/api/publicacion/${postId}/comentarios`;

    const data = {
      "nombre": "anonimo",
      "email": "anonimo",
      "cuerpo": cuerpo,
      "publicacion": postId
    };  
    
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      response.json();
      console.log(data);
    })
    .then(data => {
      location.reload();
    })
    .catch(error => console.error('Error:', error));
  }

  // Agregar el botón de eliminar al comentario
  commentElement.appendChild(deleteButton);



  function deleteComment(commentId, commentElement) {
    const url = `http://localhost:8080/api/comentarios/${commentId}`;

    fetch(url, {
      method: 'DELETE',
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al eliminar el comentario');
      }
      console.log('Comentario eliminado'); // Para verificar que el comentario se está eliminando
      commentElement.remove(); // Elimina el comentario del DOM
    })
    .catch(error => console.error('Error:', error));
  }

  

  
});
