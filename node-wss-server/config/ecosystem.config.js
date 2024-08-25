module.exports = {
  apps: [
    {
      name: "app",
      script: "./app.js",
      instances: 4,
      exec_mode: "cluster",
    },
  ],
};
