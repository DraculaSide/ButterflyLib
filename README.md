# ButterflyApi

Welcome to the BUtterflyApi project. This README file will guide you in understanding the purpose of the project, how to set it up, and how to use the API with a simple example.

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [Example](#example)
- [Contributing](#contributing)
- [License](#license)

## Introduction

BUtterflyApi is designed to provide comprehensive data about butterflies. It allows users to query information such as species, habitats, and more.

## Installation

To get started with BUtterflyApi, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/BUtterflyApi.git
    ```

2. Navigate to the project directory:
    ```bash
    cd BUtterflyApi
    ```

3. Install the required dependencies:
    ```bash
    ./gradlew clean build
    ```

## Usage

Once the API is set up, you can start the server using the following command:

```bash
./gradlew bootRun
```

The API will be accessible at `http://localhost:8080`.

## Example

Here is a simple example of how to use the BUtterflyApi:

### Fetch Butterfly Species Information

To fetch information about a specific butterfly species, make a GET request to the `/api/butterflies/{speciesId}` endpoint.

#### Request

```bash
curl -X GET "http://localhost:8080/api/butterflies/1"
```

#### Response

```json
{
  "speciesId": 1,
  "name": "Monarch Butterfly",
  "habitat": "Meadows, Prairies",
  "description": "The Monarch butterfly is known for its orange and black wings."
}
```

### Fetch All Butterfly Species

To fetch a list of all butterfly species, make a GET request to the `/api/butterflies` endpoint.

#### Request

```bash
curl -X GET "http://localhost:8080/api/butterflies"
```

#### Response

```json
[
  {
    "speciesId": 1,
    "name": "Monarch Butterfly",
    "habitat": "Meadows, Prairies",
    "description": "The Monarch butterfly is known for its orange and black wings."
  },
  {
    "speciesId": 2,
    "name": "Swallowtail Butterfly",
    "habitat": "Forests, Gardens",
    "description": "Swallowtail butterflies are vibrant with large wings and tail-like extensions."
  }
]
```

## Contributing

If you would like to contribute to this project, please fork the repository and submit a pull request. Make sure to follow the contribution guidelines.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.