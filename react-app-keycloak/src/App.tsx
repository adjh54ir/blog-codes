import React from "react";
import "./App.css";

const App = () => {
  const openKeycloak = () => {
    const authUrl =
      "http://localhost:8080/auth/realms/myrealm/protocol/openid-connect/auth";
    const clientId = "spa-client";
    const redirectUri = "http://localhost:3000/callback";

    const authRequest = `${authUrl}?
  response_type=token
  &client_id=${clientId}
  &redirect_uri=${redirectUri}
  &scope=openid profile`;

    // 브라우저 리다이렉트
    window.location.href = authRequest;
  };

  return (
    <div className="App">
      <header className="App-header"></header>
    </div>
  );
};

export default App;
