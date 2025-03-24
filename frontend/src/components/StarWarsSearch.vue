<template>
    <div class="form-container">
      <h1>Star Wars Search</h1>
  
      <div class="form-group">
        <label >Select Type:</label>
        <select v-model="type">
          <option value="people">People</option>
          <option value="planets">Planets</option>
          <option value="species">Species</option>
        </select>
      </div>
  
      <div class="form-group">
        <label>Enter Name:</label>
        <input v-model="name" type="text" placeholder="e.g., Luke Skywalker" />
      </div>
  
      <div class="form-group">
        <input type="checkbox" v-model="offlineMode" />
        <label>Use Offline Mode</label>
      </div>
  
      <button @click="fetchData" :disabled="loading">
        {{ loading ? 'Searching...' : 'Search' }}
      </button>
  
      <div v-if="error">{{ error }}</div>
  
      <div v-if="result">
        <p><strong>Type:</strong> {{ result.type }}</p>
        <p><strong>Count:</strong> {{ result.count }}</p>
        <p><strong>Name</strong>{{ result.name }}</p>
        <p><strong>Films:</strong> <span v-if="result.films.length">{{ result.films.join(', ') }}</span> <span v-else>None</span></p>
      </div>
    </div>
  </template>

<script setup>
import { ref } from 'vue';

const type = ref('people'); // Default type
const name = ref('');
const result = ref(null);
const loading = ref(false);
const error = ref(null);
const offlineMode = ref(false);

const fetchData = async () => {
  if (!name.value.trim()) {
    error.value = 'Please enter a name';
    return;
  }

  loading.value = true;
  error.value = null;
  result.value = null;

  try {
    const response = await fetch(`http://localhost:8080/api/starwars?type=${type.value}&name=${name.value}&offline=${offlineMode.value}`);

    if (!response.ok) {
      throw new Error('Failed to fetch data');
    }

    result.value = await response.json();
    console.log(result.value)
    console.log(result.value)
  } catch (err) {
    error.value = 'Failed to fetch data. Try again later.';
  } finally {
    loading.value = false;
  }
};
</script>



<style>
body {
  background-color: #1a202c;
  font-family: 'Arial', sans-serif;
}
</style>


