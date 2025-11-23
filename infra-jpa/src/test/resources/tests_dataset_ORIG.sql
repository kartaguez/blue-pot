INSERT INTO pot(id, version, uuid, active_from, inactive_from, name) VALUES
  (1, 1, '40adf588-581c-450f-88b3-b2f399a32632', 1, 2, 'Zot Zrdèche'),
  (1, 2, '40adf588-581c-450f-88b3-b2f399a32632', 2, 3, 'Pot Ardèche')
;

INSERT INTO pot_global_version(id, version, pot_uuid, pot_business_version_value, pot_business_version_stamp) VALUES
  (1, 2, '40adf588-581c-450f-88b3-b2f399a32632', 2, 'bbbb')
;

INSERT INTO pot_shareholder(id, version, uuid, pot_uuid, active_from, inactive_from, name) VALUES
  (1, 2, 'b8e7d49b-bed9-4d3c-818a-9b9843823a39', '40adf588-581c-450f-88b3-b2f399a32632', 1, 2, 'Zlex'),
  (2, 1, 'b8e7d49b-bed9-4d3c-818a-9b9843823a39', '40adf588-581c-450f-88b3-b2f399a32632', 2, 3, 'Alex'),
  (3, 1, '5e1d3932-3f5f-4d20-9960-c5054db99240', '40adf588-581c-450f-88b3-b2f399a32632', 2, 3, 'Pot Ardèche')
;