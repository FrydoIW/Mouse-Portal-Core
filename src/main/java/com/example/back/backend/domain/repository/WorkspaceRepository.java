package com.example.back.backend.domain.repository;

import com.example.back.backend.domain.model.GlobalModel;

public interface WorkspaceRepository {

    public void insertNewWorkspace(GlobalModel globalModel) throws Exception;

    public void addWorkspace(GlobalModel globalModel) throws Exception;

    public void editWorkspace(GlobalModel globalModel) throws Exception;

}
