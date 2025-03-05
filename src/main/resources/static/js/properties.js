const getAllProperties= async () =>{
    try {
        const response = await fetch('/api/v1/properties/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Error fetching properties');
        }
        const properties = await response.json();
        renderProperties(properties);
    } catch (error) {
        console.error('Error al obtener los datos:', error);
        document.querySelector('.procedures').innerHTML = `
        <div class="error-message">
            <h2>¡Ups! Algo salió mal</h2>
            <p>No pudimos cargar las propiedades en este momento. Esto podría deberse a un problema con el servidor o la conexión a internet.</p>
            <p><strong>Por favor, intenta nuevamente más tarde.</strong></p>
            <p>Si el problema persiste, contacta al soporte técnico.</p>
        </div>
    `;
    }
};

const renderProperties = (properties) =>{
    const container = document.querySelector('.properties');
    container.innerHTML = '';
    properties.forEach(property => {
        const propertyElement = document.createElement('div');
        propertyElement.classList.add('property');
        propertyElement.id = `property-${pro.id}`;
        procedureElement.innerHTML = `
            <div class="text-section">
                <h1>${property.address}</h1>
                <p>Size: ${property.size}</p>
                <p>Description: ${property.description}</p>
                <p>Price: ${property.price}</p>
                <button class="update-button">Update</button>
                <button class="delete-button">Delete</button>
            </div>
        `
        const updateButton = document.querySelector('.update-button');
        acceptButton.addEventListener('click', (event) => {
            event.preventDefault();
            handleSubmit(procedureId);
        });
        const deleteButton = document.querySelector('.delete-button');
        cancelButton.addEventListener('click', (event) => {
            event.preventDefault();
            deleteProperty(procedureId);
        }); 
    });

    //falta arreglar esto
    async function deleteProperty(propertyId) {
        window.location.href = `/${propertyId}`;
        
    }

};
