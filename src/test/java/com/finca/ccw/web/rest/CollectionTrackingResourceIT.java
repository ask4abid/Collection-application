package com.finca.ccw.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.finca.ccw.CcwApplicationApp;
import com.finca.ccw.domain.CollectionTracking;
import com.finca.ccw.repository.CollectionTrackingRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CollectionTrackingResource} REST controller.
 */
@SpringBootTest(classes = CcwApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CollectionTrackingResourceIT {
    private static final String DEFAULT_EMPLOYEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_PROPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_PROPOSAL = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_PROPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_SUB_PROPOSAL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NO_OF_VISITS = 1;
    private static final Integer UPDATED_NO_OF_VISITS = 2;

    private static final BigDecimal DEFAULT_OS_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OS_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OS_PROFIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OS_PROFIT = new BigDecimal(2);

    private static final String DEFAULT_OD_DAYS = "AAAAAAAAAA";
    private static final String UPDATED_OD_DAYS = "BBBBBBBBBB";

    private static final String DEFAULT_LOAN_OFFICER = "AAAAAAAAAA";
    private static final String UPDATED_LOAN_OFFICER = "BBBBBBBBBB";

    private static final String DEFAULT_VISITED_BY = "AAAAAAAAAA";
    private static final String UPDATED_VISITED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PPT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PPT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMAKRS = "AAAAAAAAAA";
    private static final String UPDATED_REMAKRS = "BBBBBBBBBB";

    @Autowired
    private CollectionTrackingRepository collectionTrackingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCollectionTrackingMockMvc;

    private CollectionTracking collectionTracking;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionTracking createEntity(EntityManager em) {
        CollectionTracking collectionTracking = new CollectionTracking()
            .employeeID(DEFAULT_EMPLOYEE_ID)
            .unitID(DEFAULT_UNIT_ID)
            .employeeName(DEFAULT_EMPLOYEE_NAME)
            .businessProposal(DEFAULT_BUSINESS_PROPOSAL)
            .subProposal(DEFAULT_SUB_PROPOSAL)
            .mobileNo(DEFAULT_MOBILE_NO)
            .relationId(DEFAULT_RELATION_ID)
            .accountNo(DEFAULT_ACCOUNT_NO)
            .accountTitle(DEFAULT_ACCOUNT_TITLE)
            .noOfVisits(DEFAULT_NO_OF_VISITS)
            .osAmount(DEFAULT_OS_AMOUNT)
            .osProfit(DEFAULT_OS_PROFIT)
            .odDays(DEFAULT_OD_DAYS)
            .loanOfficer(DEFAULT_LOAN_OFFICER)
            .visitedBy(DEFAULT_VISITED_BY)
            .pptDate(DEFAULT_PPT_DATE)
            .remakrs(DEFAULT_REMAKRS);
        return collectionTracking;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionTracking createUpdatedEntity(EntityManager em) {
        CollectionTracking collectionTracking = new CollectionTracking()
            .employeeID(UPDATED_EMPLOYEE_ID)
            .unitID(UPDATED_UNIT_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .businessProposal(UPDATED_BUSINESS_PROPOSAL)
            .subProposal(UPDATED_SUB_PROPOSAL)
            .mobileNo(UPDATED_MOBILE_NO)
            .relationId(UPDATED_RELATION_ID)
            .accountNo(UPDATED_ACCOUNT_NO)
            .accountTitle(UPDATED_ACCOUNT_TITLE)
            .noOfVisits(UPDATED_NO_OF_VISITS)
            .osAmount(UPDATED_OS_AMOUNT)
            .osProfit(UPDATED_OS_PROFIT)
            .odDays(UPDATED_OD_DAYS)
            .loanOfficer(UPDATED_LOAN_OFFICER)
            .visitedBy(UPDATED_VISITED_BY)
            .pptDate(UPDATED_PPT_DATE)
            .remakrs(UPDATED_REMAKRS);
        return collectionTracking;
    }

    @BeforeEach
    public void initTest() {
        collectionTracking = createEntity(em);
    }

    @Test
    @Transactional
    public void createCollectionTracking() throws Exception {
        int databaseSizeBeforeCreate = collectionTrackingRepository.findAll().size();
        // Create the CollectionTracking
        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isCreated());

        // Validate the CollectionTracking in the database
        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeCreate + 1);
        CollectionTracking testCollectionTracking = collectionTrackingList.get(collectionTrackingList.size() - 1);
        assertThat(testCollectionTracking.getEmployeeID()).isEqualTo(DEFAULT_EMPLOYEE_ID);
        assertThat(testCollectionTracking.getUnitID()).isEqualTo(DEFAULT_UNIT_ID);
        assertThat(testCollectionTracking.getEmployeeName()).isEqualTo(DEFAULT_EMPLOYEE_NAME);
        assertThat(testCollectionTracking.getBusinessProposal()).isEqualTo(DEFAULT_BUSINESS_PROPOSAL);
        assertThat(testCollectionTracking.getSubProposal()).isEqualTo(DEFAULT_SUB_PROPOSAL);
        assertThat(testCollectionTracking.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testCollectionTracking.getRelationId()).isEqualTo(DEFAULT_RELATION_ID);
        assertThat(testCollectionTracking.getAccountNo()).isEqualTo(DEFAULT_ACCOUNT_NO);
        assertThat(testCollectionTracking.getAccountTitle()).isEqualTo(DEFAULT_ACCOUNT_TITLE);
        assertThat(testCollectionTracking.getNoOfVisits()).isEqualTo(DEFAULT_NO_OF_VISITS);
        assertThat(testCollectionTracking.getOsAmount()).isEqualTo(DEFAULT_OS_AMOUNT);
        assertThat(testCollectionTracking.getOsProfit()).isEqualTo(DEFAULT_OS_PROFIT);
        assertThat(testCollectionTracking.getOdDays()).isEqualTo(DEFAULT_OD_DAYS);
        assertThat(testCollectionTracking.getLoanOfficer()).isEqualTo(DEFAULT_LOAN_OFFICER);
        assertThat(testCollectionTracking.getVisitedBy()).isEqualTo(DEFAULT_VISITED_BY);
        assertThat(testCollectionTracking.getPptDate()).isEqualTo(DEFAULT_PPT_DATE);
        assertThat(testCollectionTracking.getRemakrs()).isEqualTo(DEFAULT_REMAKRS);
    }

    @Test
    @Transactional
    public void createCollectionTrackingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = collectionTrackingRepository.findAll().size();

        // Create the CollectionTracking with an existing ID
        collectionTracking.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionTracking in the database
        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEmployeeIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setEmployeeID(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setUnitID(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmployeeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setEmployeeName(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessProposalIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setBusinessProposal(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSubProposalIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setSubProposal(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setMobileNo(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setRelationId(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setAccountNo(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setAccountTitle(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoOfVisitsIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setNoOfVisits(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOsAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setOsAmount(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOsProfitIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setOsProfit(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOdDaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setOdDays(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoanOfficerIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setLoanOfficer(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVisitedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setVisitedBy(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPptDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setPptDate(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemakrsIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTrackingRepository.findAll().size();
        // set the field null
        collectionTracking.setRemakrs(null);

        // Create the CollectionTracking, which fails.

        restCollectionTrackingMockMvc
            .perform(
                post("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCollectionTrackings() throws Exception {
        // Initialize the database
        collectionTrackingRepository.saveAndFlush(collectionTracking);

        // Get all the collectionTrackingList
        restCollectionTrackingMockMvc
            .perform(get("/api/collection-trackings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(collectionTracking.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeID").value(hasItem(DEFAULT_EMPLOYEE_ID)))
            .andExpect(jsonPath("$.[*].unitID").value(hasItem(DEFAULT_UNIT_ID)))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME)))
            .andExpect(jsonPath("$.[*].businessProposal").value(hasItem(DEFAULT_BUSINESS_PROPOSAL)))
            .andExpect(jsonPath("$.[*].subProposal").value(hasItem(DEFAULT_SUB_PROPOSAL)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].relationId").value(hasItem(DEFAULT_RELATION_ID)))
            .andExpect(jsonPath("$.[*].accountNo").value(hasItem(DEFAULT_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].accountTitle").value(hasItem(DEFAULT_ACCOUNT_TITLE)))
            .andExpect(jsonPath("$.[*].noOfVisits").value(hasItem(DEFAULT_NO_OF_VISITS)))
            .andExpect(jsonPath("$.[*].osAmount").value(hasItem(DEFAULT_OS_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].osProfit").value(hasItem(DEFAULT_OS_PROFIT.intValue())))
            .andExpect(jsonPath("$.[*].odDays").value(hasItem(DEFAULT_OD_DAYS)))
            .andExpect(jsonPath("$.[*].loanOfficer").value(hasItem(DEFAULT_LOAN_OFFICER)))
            .andExpect(jsonPath("$.[*].visitedBy").value(hasItem(DEFAULT_VISITED_BY)))
            .andExpect(jsonPath("$.[*].pptDate").value(hasItem(DEFAULT_PPT_DATE.toString())))
            .andExpect(jsonPath("$.[*].remakrs").value(hasItem(DEFAULT_REMAKRS)));
    }

    @Test
    @Transactional
    public void getCollectionTracking() throws Exception {
        // Initialize the database
        collectionTrackingRepository.saveAndFlush(collectionTracking);

        // Get the collectionTracking
        restCollectionTrackingMockMvc
            .perform(get("/api/collection-trackings/{id}", collectionTracking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(collectionTracking.getId().intValue()))
            .andExpect(jsonPath("$.employeeID").value(DEFAULT_EMPLOYEE_ID))
            .andExpect(jsonPath("$.unitID").value(DEFAULT_UNIT_ID))
            .andExpect(jsonPath("$.employeeName").value(DEFAULT_EMPLOYEE_NAME))
            .andExpect(jsonPath("$.businessProposal").value(DEFAULT_BUSINESS_PROPOSAL))
            .andExpect(jsonPath("$.subProposal").value(DEFAULT_SUB_PROPOSAL))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO))
            .andExpect(jsonPath("$.relationId").value(DEFAULT_RELATION_ID))
            .andExpect(jsonPath("$.accountNo").value(DEFAULT_ACCOUNT_NO))
            .andExpect(jsonPath("$.accountTitle").value(DEFAULT_ACCOUNT_TITLE))
            .andExpect(jsonPath("$.noOfVisits").value(DEFAULT_NO_OF_VISITS))
            .andExpect(jsonPath("$.osAmount").value(DEFAULT_OS_AMOUNT.intValue()))
            .andExpect(jsonPath("$.osProfit").value(DEFAULT_OS_PROFIT.intValue()))
            .andExpect(jsonPath("$.odDays").value(DEFAULT_OD_DAYS))
            .andExpect(jsonPath("$.loanOfficer").value(DEFAULT_LOAN_OFFICER))
            .andExpect(jsonPath("$.visitedBy").value(DEFAULT_VISITED_BY))
            .andExpect(jsonPath("$.pptDate").value(DEFAULT_PPT_DATE.toString()))
            .andExpect(jsonPath("$.remakrs").value(DEFAULT_REMAKRS));
    }

    @Test
    @Transactional
    public void getNonExistingCollectionTracking() throws Exception {
        // Get the collectionTracking
        restCollectionTrackingMockMvc.perform(get("/api/collection-trackings/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollectionTracking() throws Exception {
        // Initialize the database
        collectionTrackingRepository.saveAndFlush(collectionTracking);

        int databaseSizeBeforeUpdate = collectionTrackingRepository.findAll().size();

        // Update the collectionTracking
        CollectionTracking updatedCollectionTracking = collectionTrackingRepository.findById(collectionTracking.getId()).get();
        // Disconnect from session so that the updates on updatedCollectionTracking are not directly saved in db
        em.detach(updatedCollectionTracking);
        updatedCollectionTracking
            .employeeID(UPDATED_EMPLOYEE_ID)
            .unitID(UPDATED_UNIT_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .businessProposal(UPDATED_BUSINESS_PROPOSAL)
            .subProposal(UPDATED_SUB_PROPOSAL)
            .mobileNo(UPDATED_MOBILE_NO)
            .relationId(UPDATED_RELATION_ID)
            .accountNo(UPDATED_ACCOUNT_NO)
            .accountTitle(UPDATED_ACCOUNT_TITLE)
            .noOfVisits(UPDATED_NO_OF_VISITS)
            .osAmount(UPDATED_OS_AMOUNT)
            .osProfit(UPDATED_OS_PROFIT)
            .odDays(UPDATED_OD_DAYS)
            .loanOfficer(UPDATED_LOAN_OFFICER)
            .visitedBy(UPDATED_VISITED_BY)
            .pptDate(UPDATED_PPT_DATE)
            .remakrs(UPDATED_REMAKRS);

        restCollectionTrackingMockMvc
            .perform(
                put("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCollectionTracking))
            )
            .andExpect(status().isOk());

        // Validate the CollectionTracking in the database
        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeUpdate);
        CollectionTracking testCollectionTracking = collectionTrackingList.get(collectionTrackingList.size() - 1);
        assertThat(testCollectionTracking.getEmployeeID()).isEqualTo(UPDATED_EMPLOYEE_ID);
        assertThat(testCollectionTracking.getUnitID()).isEqualTo(UPDATED_UNIT_ID);
        assertThat(testCollectionTracking.getEmployeeName()).isEqualTo(UPDATED_EMPLOYEE_NAME);
        assertThat(testCollectionTracking.getBusinessProposal()).isEqualTo(UPDATED_BUSINESS_PROPOSAL);
        assertThat(testCollectionTracking.getSubProposal()).isEqualTo(UPDATED_SUB_PROPOSAL);
        assertThat(testCollectionTracking.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testCollectionTracking.getRelationId()).isEqualTo(UPDATED_RELATION_ID);
        assertThat(testCollectionTracking.getAccountNo()).isEqualTo(UPDATED_ACCOUNT_NO);
        assertThat(testCollectionTracking.getAccountTitle()).isEqualTo(UPDATED_ACCOUNT_TITLE);
        assertThat(testCollectionTracking.getNoOfVisits()).isEqualTo(UPDATED_NO_OF_VISITS);
        assertThat(testCollectionTracking.getOsAmount()).isEqualTo(UPDATED_OS_AMOUNT);
        assertThat(testCollectionTracking.getOsProfit()).isEqualTo(UPDATED_OS_PROFIT);
        assertThat(testCollectionTracking.getOdDays()).isEqualTo(UPDATED_OD_DAYS);
        assertThat(testCollectionTracking.getLoanOfficer()).isEqualTo(UPDATED_LOAN_OFFICER);
        assertThat(testCollectionTracking.getVisitedBy()).isEqualTo(UPDATED_VISITED_BY);
        assertThat(testCollectionTracking.getPptDate()).isEqualTo(UPDATED_PPT_DATE);
        assertThat(testCollectionTracking.getRemakrs()).isEqualTo(UPDATED_REMAKRS);
    }

    @Test
    @Transactional
    public void updateNonExistingCollectionTracking() throws Exception {
        int databaseSizeBeforeUpdate = collectionTrackingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCollectionTrackingMockMvc
            .perform(
                put("/api/collection-trackings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(collectionTracking))
            )
            .andExpect(status().isBadRequest());

        // Validate the CollectionTracking in the database
        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCollectionTracking() throws Exception {
        // Initialize the database
        collectionTrackingRepository.saveAndFlush(collectionTracking);

        int databaseSizeBeforeDelete = collectionTrackingRepository.findAll().size();

        // Delete the collectionTracking
        restCollectionTrackingMockMvc
            .perform(delete("/api/collection-trackings/{id}", collectionTracking.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CollectionTracking> collectionTrackingList = collectionTrackingRepository.findAll();
        assertThat(collectionTrackingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
