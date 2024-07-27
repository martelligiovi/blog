const apiUrl = 'http://localhost:8080/api/publicacion?pagenumber='; // Reemplaza con la URL de tu API

document.addEventListener('DOMContentLoaded', () => {
  const content = document.getElementById('content');

  // Obtener y mostrar las publicaciones
  fetchPosts(0);
  paginador(apiUrl + '0');

  async function fetchPosts(pageNumber) {
    try {
      const response = await fetch(apiUrl + pageNumber);
      console.log('Estado de la respuesta:', response.status);
      
      const responseData = await response.text();
  
      const data = JSON.parse(responseData);
      const posts = data.publicaciones;
      displayPosts(posts);
    } catch (error) {
      console.error('Error al obtener las publicaciones:', error);
    }
  }
  
  document.getElementById('newPostButton').addEventListener('click', function() {
    document.getElementById('newPostForm').style.display = 'block';
  });
  
  document.getElementById('newPostForm').addEventListener('submit', function(event) {
    event.preventDefault();
    var titulo = document.getElementById('titulo').value;
    var contenido = document.getElementById('contenido').value;
    var descripcion = document.getElementById('descripcion').value;
    sendPost(titulo, contenido, descripcion);
  });
  
  

  function displayPosts(posts) {
    content.innerHTML = ''; // Limpiar el contenido actual

    posts.forEach(post => {
      const postElement = createPostElement(post);
      content.appendChild(postElement);
    });
  }

  function createPostElement(post) {
    const postElement = document.createElement('div');
    postElement.classList.add('post');
  
    const titleElement = document.createElement('h2');
    titleElement.textContent = post.titulo;
  
    const descriptionElement = document.createElement('p');
    descriptionElement.textContent = post.descripcion;
  
    const contentElement = document.createElement('p');
    contentElement.textContent = truncateText(post.contenido, 204); // Limitar a 204 caracteres
    contentElement.style.minHeight = '72px'; // Establecer la altura mínima
  
    const readMoreButton = document.createElement('button');
    readMoreButton.textContent = 'Leer Más';
    readMoreButton.addEventListener('click', () => openPost(post.id));
  
    // Crear botones de editar y eliminar
    const deleteButton = document.createElement('button');
  
    // Agregar texto a los botones
    deleteButton.textContent = 'Eliminar';

    // Crear contenedores para los botones
    const leftButtonContainer = document.createElement('div');
    leftButtonContainer.id = 'button-edit-eliminar';
    const rightButtonContainer = document.createElement('div');

    // Asignar las clases a los contenedores
    leftButtonContainer.className = 'left-button-container';
    rightButtonContainer.className = 'right-button-container';

    // Agregar el botón de "Leer Más" al contenedor de la izquierda
    leftButtonContainer.appendChild(readMoreButton);

    // Agregar los botones de "Editar" y "Eliminar" al contenedor de la derecha
    rightButtonContainer.appendChild(deleteButton);

    // Agregar los contenedores de botones al elemento de la publicación
    postElement.appendChild(leftButtonContainer);
    postElement.appendChild(rightButtonContainer);

    // Limpiar el flotado
    const clearDiv = document.createElement('div');
    clearDiv.style.clear = 'both';
    postElement.appendChild(clearDiv);
  
    // Adjuntar eventos de clic a los botones
  
  
    deleteButton.addEventListener('click', () => {
      const url = `http://localhost:8080/api/publicacion/${post.id}`;
  
      fetch(url, {
        method: 'DELETE',
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al eliminar la publicación');
        }
        postElement.remove(); // Elimina la publicación del DOM
      })
      .catch(error => console.error('Error:', error));
    });
  
    postElement.appendChild(titleElement);
    postElement.appendChild(descriptionElement);
    postElement.appendChild(contentElement);
    
  
    return postElement;
  }
  
  // Función para truncar el texto y añadir "..."
  function truncateText(text, maxLength) {
    if (text.length > maxLength) {
      return text.substring(0, maxLength) + '...';
    }
    return text;
  }
  
  function openPost(postId) {
    window.location.href = `post.html?id=${postId}`;
  }

  document.querySelector('.contenido').style.border = 'none';

  


  function sendPost(titulo, contenido, descripcion) {
    const url = `http://localhost:8080/api/publicacion`;
    const post = {
      titulo: titulo,
      contenido: contenido,
      descripcion: descripcion
    };
  
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(post)
    })
    .then(response => response.json())
    .then(post=> {
      console.log('Success:', post);
      location.reload();
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  }



  async function paginador(apiUrl) {
    try {
      const response = await fetch(apiUrl);
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      const data = await response.json();
      console.log('Data received:', data);
      
      const totalPaginas = data.totalPaginas;
      console.log('Total pages:', totalPaginas);
      
      const paginatorDiv = document.querySelector('#paginator');
      console.log('Paginator div:', paginatorDiv);
      
      if (!paginatorDiv) {
        throw new Error('No se encontró el elemento #paginator');
      }
      
      paginatorDiv.innerHTML = '';
      
      for (let i = 1; i <= totalPaginas; i++) {
        const button = document.createElement('button');
        button.textContent = i;
        button.value = i;
        
        button.addEventListener('click', () => {
          fetchPosts(i - 1);
        });
        
        paginatorDiv.appendChild(button);
      }
      
      console.log('Buttons created:', paginatorDiv.children.length);
    } catch (error) {
      console.error('Error:', error);
    }
  }
  



});