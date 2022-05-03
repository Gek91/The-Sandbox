package service;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetFactory;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.ColumnType;
import com.smartsheet.api.models.enums.DestinationType;
import com.smartsheet.api.models.enums.RowInclusion;
import com.smartsheet.api.models.enums.SummaryFieldExclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SmartsheetService {

	private transient Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Logger getLogger() {
		return logger;
	}

	private Smartsheet smartsheet;

	private SmartsheetService(Smartsheet smartsheet)  {
		this.smartsheet = smartsheet;
	}

	public static SmartsheetService buildService(String credential) {
		SmartsheetService service = new SmartsheetService(SmartsheetFactory.createDefaultClient(credential));
		return service;
	}

	private Smartsheet getService() {
		return this.smartsheet;
	}

	/*
		List children folder, API call depends on parent type (workspace or another folder)
	 */
	public PagedResult<Folder> listChildrenFolders(long parentId, DestinationType parentType, PaginationParameters paginationParameter) {

		SmartsheetBiFunction<Long, PaginationParameters, PagedResult<Folder>> function;

		if(parentType.equals(DestinationType.FOLDER)) {
			function = (a, b) -> getService().folderResources().listFolders(a, b);
		} else if(parentType.equals(DestinationType.WORKSPACE)) {
			function = (a, b) -> getService().workspaceResources().folderResources().listFolders(a, b);
		} else {
			throw new InvalidParameterException();
		}

		Callable<PagedResult<Folder>> call = () -> function.apply(parentId, paginationParameter);

		return executeRemoteCallWithIncrementalBackoff(call, "listing children folders");
	}

	/*
		Create new folder in a parent element, API call depends on parent type (workspace or another folder)
	 */
	public Folder createFolderInParent(long parentId, String folderName, DestinationType parentType) {

		Folder folder = new Folder();
		folder.setName(folderName);

		SmartsheetBiFunction<Long, Folder, Folder> function;

		if(parentType.equals(DestinationType.FOLDER)) {
			function = (a, b) -> getService().folderResources().createFolder(a, b);
		} else if(parentType.equals(DestinationType.WORKSPACE)) {
			function = (a, b) -> getService().workspaceResources().folderResources().createFolder(a, b);
		} else {
			throw new InvalidParameterException();
		}

		Callable<Folder> call = () -> function.apply(parentId, folder);

		return executeRemoteCallWithIncrementalBackoff(call, "creating folder");
	}

	/*
		Retrieve sheet detail
	 */
	public Sheet getSheetDetail(long sheetId) {

		Callable<Sheet> call = () -> getService().sheetResources().getSheet(
				sheetId,                // long sheetId
				null,           // EnumSet<SheetInclusion> includes
				null,           // EnumSet<ObjectExclusion> excludes
				null,             // Set<Long> rowIds
				null,         // Set<Integer> rowNumbers
				null,           // Set<Long> columnIds
				null,            // Integer pageSize
				null                // Integer page
		);

		return executeRemoteCallWithIncrementalBackoff(call, "getting sheet detail");
	}

	/*
		Retrieve sheet summary
	 */
	public SheetSummary getSheetSummary(long sheetId) {

		Callable<SheetSummary> call = () -> getService().sheetResources().summaryResources().getSheetSummary(sheetId, null, EnumSet.of(SummaryFieldExclusion.IMAGE));

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving sheet summary for sheet " + sheetId);
	}

	/*
		Move a folder
	 */
	public void moveFolder(long folderId, ContainerDestination containerDestination) {

		Callable<Folder> call = () -> getService().folderResources().moveFolder(folderId, containerDestination);

		executeRemoteCallWithIncrementalBackoff(call, "moving folder " + folderId);
	}

	/*
		Retrieve sheet row detail data
	 */
	public Row getRowDetail(long sheetId, long rowId) {

		Callable<Row> call = () -> getService().sheetResources().rowResources().getRow(sheetId, rowId,
				EnumSet.of(RowInclusion.OBJECT_VALUE, RowInclusion.COLUMNS, RowInclusion.COLUMN_TYPE), null);

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving row " + rowId);
	}

	/*
		Create row discussion
	 */
	public void createSheetRowDiscussion(long sheetId, long rowId, Discussion discussion) {

		Callable<Discussion> call = () -> getService().sheetResources().rowResources().discussionResources().createDiscussion(sheetId, rowId, discussion);

		executeRemoteCallWithIncrementalBackoff(call, "creating discussion in sheet " + sheetId);
	}

	/*
		Update sheet rows data
	 */
	public void updateRowsValues(long sheetId , List<Row> rows) {

		Callable<List<Row>> call = () -> getService().sheetResources().rowResources().updateRows(sheetId, rows);

		executeRemoteCallWithIncrementalBackoff(call, "updating row data " + sheetId);
	}

	/*
		Insert new rows in sheet
	 */
	public void insertRows(long sheetId, List<Row> rows) {

		Callable<List<Row>> call = () -> getService().sheetResources().rowResources().addRows(
				sheetId,
				rows);

		executeRemoteCallWithIncrementalBackoff(call, "inserting rows data");
	}

	/*
		Update sheet multipicklist column values
	 */
	public void updateColumnOptions(long sheetId, long columnId, List<String> options) {

		Column columnSpecification = new Column(columnId).setType(ColumnType.MULTI_PICKLIST)
				.setOptions(options);

		Callable<Column> call = () -> getService().sheetResources().columnResources().updateColumn(sheetId, columnSpecification);

		executeRemoteCallWithIncrementalBackoff(call, "updating column options");
	}

	/*
		Retrieve workspace data
	 */
	public Workspace getWorkspaceData(long workspaceId) {

		Callable<Workspace> call = () -> getService().workspaceResources().getWorkspace(workspaceId, true, null);

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving workspace");
	}

	/*
		Retrieve workspace list
	 */
	public PagedResult<Workspace> listWorkspaces(PaginationParameters paginationParameter) {

		Callable<PagedResult<Workspace>> call = () -> getService().workspaceResources().listWorkspaces(paginationParameter);

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving workspaces");
	}

	/*
		create a new workspace
	 */
	public Workspace createWorkspace(String name) {

		Workspace workspace = new Workspace();
		workspace.setName(name);

		Callable<Workspace> call = () -> getService().workspaceResources().createWorkspace(workspace);

		return executeRemoteCallWithIncrementalBackoff(call, "create workspaces " + name);
	}

	/*
		Add shares to workspace
	 */
	public void shareWorkspace(long workspaceId, List<Share> shares) {

		Callable<List<Share>> call = () -> getService().workspaceResources().shareResources().shareTo(workspaceId, shares, false);

		executeRemoteCallWithIncrementalBackoff(call, "share workspace " + workspaceId);
	}

	/*
		Retrieve workspace shares list
	 */
	public PagedResult<Share> getWorkspaceShares(long workspaceId, PaginationParameters parameters) {

		Callable<PagedResult<com.smartsheet.api.models.Share>> call = () -> getService().workspaceResources().shareResources().listShares(workspaceId, parameters);

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving workspace shares for workspace " + workspaceId);
	}
	/*
		Add shares to sheet
	 */
	public void shareSheet(long sheetId, List<Share> shares) {

		Callable<List<Share>> call = () ->  getService().sheetResources().shareResources().shareTo(sheetId, shares, false);

		executeRemoteCallWithIncrementalBackoff(call, "share sheet " + sheetId);
	}

	/*
		Retrieve sheet shares list
	 */
	public PagedResult<Share> getSheetShares(long sheetId, PaginationParameters parameters) {

		Callable<PagedResult<Share>> call = () ->  getService().sheetResources().shareResources().listShares(sheetId, parameters, true);

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving sheet shares for sheet " + sheetId);
	}

	/*
		Retrieve groups list
	 */
	public List<Group> getGroups() {

		PaginationParameters parameters = new PaginationParameters();
		parameters.setIncludeAll(true);

		Callable<List<Group>> call = () -> getService().groupResources().listGroups(parameters).getData();

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving groups");
	}

	/*
		Retrieve group detail
	 */
	public Group getGroup(long groupId) {

		Callable<Group> call = () -> getService().groupResources().getGroup(groupId);

		return executeRemoteCallWithIncrementalBackoff(call, "retrieving group " + groupId);
	}

	/*
		Create new group
	 */
	public Group insertGroup(Group group) {

		Callable<Group> call = () -> getService().groupResources().createGroup(group);

		return executeRemoteCallWithIncrementalBackoff(call, "creating group");
	}

	/*
		add group members
	 */
	public void insertGroupMembers(long groupId, List<GroupMember> members) {

		Callable<List<GroupMember>> call = () -> getService().groupResources().memberResources().addGroupMembers(groupId, members);

		executeRemoteCallWithIncrementalBackoff(call, "inserting members in group " + groupId);
	}

	/*
		remove group member
	 */
	public void removeGroupMember(long groupId, long memberId) {

		Callable<Void> call = () -> { getService().groupResources().memberResources().deleteGroupMember(groupId, memberId); return null;};

		executeRemoteCallWithIncrementalBackoff(call, "removing members in group " + groupId);
	}

	/*
		incremental backoff retry logic API call
 	*/
	private <T> T executeRemoteCallWithIncrementalBackoff(Callable<T> remoteCall, String entityName) {

		Exception lastException = null;
		int retry = 0;

		while(retry < 3 ) {
			try {

				return remoteCall.call();
			} catch (Exception e) {
				getLogger().warn("Error {}, retry", entityName);
				retry++;
				lastException = e;
				try { TimeUnit.SECONDS.sleep(retry * retry); } catch (InterruptedException e1) { }
			}
		}

		getLogger().error("Error {}, abort", entityName);
		throw new RuntimeException(lastException);
	}
}
