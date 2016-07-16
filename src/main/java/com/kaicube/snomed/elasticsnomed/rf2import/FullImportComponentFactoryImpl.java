package com.kaicube.snomed.elasticsnomed.rf2import;

import com.kaicube.snomed.elasticsnomed.services.BranchService;
import com.kaicube.snomed.elasticsnomed.services.ConceptService;
import org.ihtsdo.otf.snomedboot.factory.HistoryAwareComponentFactory;

public class FullImportComponentFactoryImpl extends ImportComponentFactoryImpl implements HistoryAwareComponentFactory {

	private String basePath;

	public FullImportComponentFactoryImpl(ConceptService conceptService, BranchService branchService, String path) {
		super(conceptService, branchService, path);
		this.basePath = path;
	}

	@Override
	public void loadingReleaseDeltaStarting(String releaseDate) {
		setCommit(getBranchService().openCommit(basePath));
	}

	@Override
	public void loadingReleaseDeltaFinished(String releaseDate) {
		completeImportCommit();
		getBranchService().create(basePath + "/" + releaseDate);
	}

	@Override
	public void loadingComponentsStarting() {
	}

	@Override
	public void loadingComponentsCompleted() {
	}
}
