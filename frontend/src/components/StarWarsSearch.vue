<template>
  <div class="form-container">
    <h1>Star Wars Search</h1>

    <div class="form-group">
      <label>Select Type:</label>
      <select v-model="type">
        <option value="people">People</option>
        <option value="planets">Planets</option>
        <option value="species">Species</option>
        <option value="starships">Starships</option>
        <option value="vehicles">Vehicles</option>
        
        
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
      <p><strong>Name:</strong> {{ result.name }}</p>
      <p>
        <strong>Films:</strong>
        <span v-if="result.films.length">{{ result.films.join(', ') }}</span>
        <span v-else>None</span>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { fetchStarWarsData } from '../services/starWarsService';
import '../assets/style.css';

const type = ref('people');
const name = ref('');
const result = ref(null);
const loading = ref(false);
const error = ref(null);
const offlineMode = ref(false);

const fetchData = async () => {
  loading.value = true;
  error.value = null;
  result.value = null;

  try {
    result.value = await fetchStarWarsData(type.value, name.value, offlineMode.value);
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};
</script>
