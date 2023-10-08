package se331.lab08_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab08_1.dao.ParticipantDao;
import se331.lab08_1.entity.Participant;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{
    final ParticipantDao participantDao;
    @Override
    public List<Participant> getAllParticipants(){
        return participantDao.getParticipant(Pageable.unpaged()).getContent();
    }
    @Override
    public Page<Participant> getParticipant(Integer page,Integer pageSize){
        return participantDao.getParticipant(PageRequest.of(page,pageSize));
    }

}
