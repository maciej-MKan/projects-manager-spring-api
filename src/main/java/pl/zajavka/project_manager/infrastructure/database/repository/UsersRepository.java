package pl.zajavka.project_manager.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.project_manager.business.dao.UsersDAO;
import pl.zajavka.project_manager.domian.User;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class UsersRepository implements UsersDAO {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public List<User> findAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(userEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public User findUserById(Integer userId) {
        return userJpaRepository.findById(userId)
                .map(userEntityMapper::mapFromEntity)
                .orElseThrow();
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userEntityMapper.mapToEntity(user);
        UserEntity savedUser = userJpaRepository.save(userEntity);
        return userEntityMapper.mapFromEntity(savedUser);
    }

    @Override
    public void deleteUser(User user) {
        userJpaRepository.deleteById(user.getUserId());
    }
}
