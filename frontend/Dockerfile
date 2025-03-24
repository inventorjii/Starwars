# Stage 1: Build the Vue.js app
FROM node:18-alpine as build-stage

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire application code to the working directory
COPY . .

# Build the Vue.js app for production
RUN npm run build

# Stage 2: Serve the app using Nginx
FROM nginx:alpine

# Copy the production build output from the previous stage to the Nginx directory
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Expose port 80 to the outside world
EXPOSE 80

# Start Nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]
