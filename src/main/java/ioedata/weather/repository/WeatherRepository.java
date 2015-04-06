package ioedata.weather.repository;

import ioedata.mongodb.repository.BaseRepository;

import java.io.Serializable;

public interface WeatherRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

}
