INSERT INTO pot(id, version, uuid, active_from, inactive_from, name) VALUES
  ROW(1, 2, '40adf588-581c-450f-88b3-b2f399a32632', 1, 2, 'Zot Zrdèche'),
  ROW(2, 1, '40adf588-581c-450f-88b3-b2f399a32632', 2, NULL, 'Pot Ardèche')
;

INSERT INTO pot_global_version(id, version, pot_uuid, pot_business_version_value, pot_business_version_stamp) VALUES
  ROW(1, 2, '40adf588-581c-450f-88b3-b2f399a32632', 2, 'bbbb')
;
