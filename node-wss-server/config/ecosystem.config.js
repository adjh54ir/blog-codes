module.exports = {
  apps: [
    {
      name: "app-develop",
      script: "./app.js",
      instances: 4,
      exec_mode: "cluster",
      env: {
        NODE_ENV: "develop",
        PORT: 3001,
      },
    },
    {
      name: "app-stg",
      script: "./app.js",
      instances: 2,
      exec_mode: "cluster",
      env: {
        NODE_ENV: "staging",
        PORT: 3001,
      },
    },
    {
      name: "app-prd",
      script: "./app.js",
      instances: "max",
      exec_mode: "cluster",
      env: {
        NODE_ENV: "production",
        PORT: 8080,
      },
    },
  ],
};
