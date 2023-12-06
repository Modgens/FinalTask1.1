package com.example.tt.services;

import com.example.tt.exceptions.EntityNotFoundException;
import com.example.tt.models.dto.responses.HistoryResponse;
import com.example.tt.models.entities.Group;
import com.example.tt.models.entities.Room;
import com.example.tt.models.entities.Visitant;
import com.example.tt.repositories.GroupRepo;
import com.example.tt.repositories.RoomRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepo groupRepository;
    private final RoomRepo roomRepository;
    private final VisitantService visitantService;

    public List<HistoryResponse> getAllGroups() {
        return groupRepository.findAllBy();
    }

    public Group getGroupById(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElse(null);
    }

    @Transactional
    public void createGroup(Group group) {
        // Витягуємо основного гостя за його ідентифікатором паспорта
        Visitant mainVisitant = visitantService.getByPassportId(group.getMainVisitant().getPassportNumber());
        group.setMainVisitant(mainVisitant);

        // Витягуємо всіх гостей за їх ідентифікаторами паспортів та зберігаємо їх в список
        List<Visitant> visitants = group.getVisitants().stream()
                .map(visitant -> visitantService.getByPassportId(visitant.getPassportNumber()))
                .collect(Collectors.toList());
        group.setVisitants(visitants);

        // Зберігаємо групу в базу даних
        Group savedGroup = groupRepository.save(group);

        // Оновлюємо посилання на групу в кімнаті
        Room room = savedGroup.getRoom();
        room.setGroup(savedGroup);
        roomRepository.save(room);
    }


    public void updateGroupDateOut(Long id, Group updatedGroup) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group existingGroup = optionalGroup.get();
            existingGroup.setDateOut(updatedGroup.getDateOut());
            groupRepository.save(existingGroup);
        } else {
            throw new EntityNotFoundException("Group not found with id " + id);
        }
    }
}
