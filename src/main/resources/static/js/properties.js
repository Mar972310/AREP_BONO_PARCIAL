async function getAllProperties() {
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
        console.log('Datos recibidos:', properties);
        renderProperties(properties);
    } catch (error) {
        console.error('Error al obtener los datos:', error);
        document.querySelector('.properties').innerHTML = `
        <div class="error-message">
            <h2>¡Ups! Algo salió mal</h2>
            <p>No pudimos cargar las propiedades en este momento. Esto podría deberse a un problema con el servidor o la conexión a internet.</p>
            <p><strong>Por favor, intenta nuevamente más tarde.</strong></p>
            <p>Si el problema persiste, contacta al soporte técnico.</p>
        </div>
    `;
    }
}

const renderProperties = (properties) => {
    const container = document.querySelector('.properties');
    container.innerHTML = '';

    properties.forEach(property => {
        const propertyElement = document.createElement('div');
        propertyElement.classList.add('property');
        propertyElement.innerHTML = `
            <div class="text-section">
                <h2>${property.address}</h2>
                <p><strong>Size:</strong> ${property.size} m²</p>
                <p><strong>Description:</strong> ${property.description}</p>
                <p><strong>Price:</strong> $${property.price.toLocaleString()}</p>
                <div class="calendar">
                    <button class="update-button" data-id="${property.id}">Update</button>
                    <button class="delete-button" data-id="${property.id}">Delete</button>
                </div> 
            </div>
        `;

        const updateButton = propertyElement.querySelector('.update-button');
        updateButton.addEventListener('click', (event) => {
            const propertyId = event.target.dataset.id;
            
        });

        const deleteButton = propertyElement.querySelector('.delete-button');
        deleteButton.addEventListener('click', (event) => {
            const propertyId = event.target.dataset.id;
            deleteProperty(propertyId); 
        });

        container.appendChild(propertyElement);
    });
};


async function create(event) {
    event.preventDefault();  

    const formData = new FormData(document.querySelector(".property-form")); 
    const propertyData = {
        address: formData.get("address"),
        price: formData.get("price"),
        size: formData.get("size"),
        description: formData.get("description"),
    };

    try {
        
        const response = await fetch("/api/v1/properties/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(propertyData)  
        });

        if (!response.ok) throw new Error(`Error saving property: ${response.statusText}`);

        await response.json();  
        window.location.href = "/home";  
    } catch (error) {
        console.error("Error saving property:", error);
        alert("There was an error saving the property. Please try again.");
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector(".property-form");
    form.addEventListener("submit", create);
    getAllProperties();  // Fetch the properties when the page is loaded
});
