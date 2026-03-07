# Flight and Passenger Tracking API

The Flight and Passenger Tracking API is a Test Driven-Development (TDD) project that I'm creating to learn Java and Spring Boot. This system will track flight and passenger information as well as generating boarding passes and checking into flights.

## API

### User Endpoints

#### GET All Users "/api/users/"

##### Accepts:

N/A

##### Expects:

N/A

##### Returns:

ResponseEntity<List<HashMap<String, Object>>>

#### GET One User "/api/users/{id}"

##### Accepts:

@PathVariable("id") UUID id

##### Expects:

- UUID id

##### Returns:

ResponseEntity<HashMap<String, Object>>

#### POST User "/api/users/"

##### Accepts:

@RequestBody HashMap<String, Object>

##### Expects

- String username
- String firstName
- String lastName
- String email
- String password

##### Returns:

ResponseEntity<HashMap<String, Object>>

## In-Memory Database

The in-memory database is currently used as a placeholder for testing and development.

### Database Class

#### Constructor

Database(String name)

#### Properties

- String name
- HashMap<String, Table> tables
- ZonedDateTime createdAt

#### Methods

- String getName()
- HashMap<String, Table> getTables()
- Table createTable(String tableName)
- void dropTable(String tableName)
- ZonedDateTime getCreatedAt()

### Table Class

#### Constructor

Table(String name)

#### Properties

- String name
- HashMap<UUID, Row> rows
- ZonedDateTime createdAt
- RowFactory rowFactory

#### Methods

- String getName()
- Row[] getRows()
- ZonedDateTime getCreatedAt()
- void insertEntry(ROWTYPE type, UUID rowId, T content)
  - Throws DataFormatException
- Row readEntry(UUID rowId)
- void updateEntry(UUID rowId, HashMap<String, Object> valuesMap)
- void deleteEntry(UUID rowId)

### Row Factory

The row factory builds different rows which store the main data models:

- User
- Flight
- Booking
  - Booking rows are not yet in place, but will be soon.

#### Constructor

RowFactory()

#### Properties

- enum ROWTYPE
  - USER
  - FLIGHT

#### Methods

- Row createRow(ROWTYPE type, UUID id, T content) throws DataFormatException

### Row Interface

#### Methods

- UUID getRowId()
- ZonedDateTime getCreatedAt()
- ZonedDateTime getUpdatedAt()
- T getContent()
- void updateRow(HashMap<String, Object> newData)

#### Constructor

Row(String rowId, HashMap<String, String> columnValuesMap)

#### Properties

- String rowId
- HashMap<String, String> columnValuesMap
- ZonedDateTime createdAt
- ZonedDateTime updatedAt

#### Methods

- String getRowId()
- ZonedDateTime getCreatedAt()
- ZonedDateTime getUpdatedAt()
- HashMap<String, String> getColumnValuesMap()
- void setUpdatedAt(ZonedDateTime newUpdatedAt)

## Classes

### User

#### Constructor

User(String username, String firstName, String lastName, String email, String password) throws DataFormatException

#### Properties

- UUID id
- Boolean active
- String username
- String firstName
- String lastName
- String email
- String password
- Double miles

#### Methods

- UUID getId()
- String getUsername()
- Boolean getActiveStatus()
- String getFirstName()
- String getLastName()
- String getEmail()
- String Password()
- Boolean comparePassword(String password)
- Double getMiles()
- HashMap<String, Object> getUserAsHashMap()
- void addMiles(Double miles)
- void removeMiles(Double miles)
- void update(HashMap<String, Object> newDetails)
- User getSafe()
  - getSafe returns the user without the password field set to NULL

### Flight

#### Constructor

Flight(Double distance, ZonedDateTime departure, Duration duration)

#### Properties

- enum STATUS
  - UPCOMING
  - EN_ROUTE
  - LANDED

- Double distance
- ZonedDateTime departure
- Duration duration
- STATUS status

#### Methods

- Double getDistance()
- STATUS getStatus()
- ZonedDateTime getDeparture()
- Duration getDuration()
- ZonedDateTime getArrival()
- void takeOff()
- private void setDeparture()
- private void setStatus()
- private ZonedDateTime now()
- void land()
- private void setDuration()

## Thanks

Thank you for reading and taking a look at my project! I'm always open to any suggestions, so if you see a way to improve my code, please let me know.
