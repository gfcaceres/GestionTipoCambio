DROP TABLE IF EXISTS TIPO_CAMBIO;

CREATE TABLE TIPO_CAMBIO (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  monedaOrigen VARCHAR(100),
  monedaDestino VARCHAR(100),
  cambioValor FLOAT
);

/*INSERT INTO TIPO_CAMBIO (monedaOrigen, monedaDestino, cambioValor) VALUES
  ('sol', 'dolar', 0.30),
  ('sol', 'euro', 0.27),
  ('sol','libra',2.6);*/




