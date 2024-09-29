package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarUpdateDTO;
import exercise.dto.CarDTO;
import exercise.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// BEGIN
@Component
public class CarMapper {
    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    public void update(CarUpdateDTO dto, Car car) {
        if (dto == null) {
            return;
        }
        if (jsonNullableMapper.isPresent(dto.getModel())) {
            car.setModel(jsonNullableMapper.unwrap(dto.getModel()));
        }

        if (jsonNullableMapper.isPresent(dto.getEnginePower())) {
            car.setEnginePower(jsonNullableMapper.unwrap(dto.getEnginePower()));
        }

        if (jsonNullableMapper.isPresent(dto.getManufacturer())) {
            car.setManufacturer(jsonNullableMapper.unwrap(dto.getManufacturer()));
        }
    }

    public CarDTO map(Car car) {
        var carDTO = new CarDTO(
        );
        carDTO.setModel(car.getModel());
        carDTO.setId(car.getId());
        carDTO.setCreatedAt(car.getCreatedAt());
        carDTO.setUpdatedAt(car.getUpdatedAt());
        carDTO.setManufacturer(car.getManufacturer());
        carDTO.setEnginePower(car.getEnginePower());
        return carDTO;
    }
    public Car map(CarCreateDTO carCreateDTO) {
        var car = new Car();
        car.setModel(carCreateDTO.getModel());
        car.setManufacturer(car.getManufacturer());
        car.setEnginePower(car.getEnginePower());

        return car;
    }
}
// END
