export const fetchStarWarsData = async (type, name, offlineMode) => {
    if (!name.trim()) {
      throw new Error('Please enter a name');
    }
  
    try {
        const response = await fetch(`http://localhost:8081/api/starwars?type=${type}&name=${name}&offline=${offlineMode}`);
    
        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.name || 'Failed to fetch data'); // Show specific error message
        }
    
        return await response.json();
      } catch (error) {
        throw new Error(error.message);
      }
  };
  