export const fetchStarWarsData = async (type, name, offlineMode) => {
    if (!name.trim()) {
      throw new Error('Please enter a name');
    }
  
    const response = await fetch(`http://localhost:8081/api/starwars?type=${type}&name=${name}&offline=${offlineMode}`);
  
    if (!response.ok) {
      throw new Error('Failed to fetch data');
    }
  
    return await response.json();
  };
  