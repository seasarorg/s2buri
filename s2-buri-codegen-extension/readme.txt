s2-buri-codegen-extension 20070221�łɂ���

��s2-buri-codegen-extension�Ƃ�

s2dao-codegen(0.2.0-SNAPSHOT�F���r�W����181)��Ǝ��Ɋg�����ĂԂ�̏�Ԗ₢���킹�@�\��ǉ��������̂ł�
s2dao-codegen�Ɋւ��Ă͂����炩��ǂ���
http://s2dao-codegen.sandbox.seasar.org/

���g������
�Es2dao-codegen�����삷������\�z
	�Ƃ肠������x�������ăG���[���Ȃ����Ȃǂ��m�F���Ă�������
�Es2-buri-codegen-extension��dicon��fm�t�H���_��s2dao-codegen�̓����t�H���_�ɏ㏑��
�Es2dao-codegen�Ɠ����悤�Ɏ��s
�����s2dao-codegen���{���o�͂���t�@�C���ƂԂ�Ή��̊g���ς݂�Dao��SQL���������Ă��܂�
�o�͂����t�@�C�����v���W�F�N�g��java��resource�t�H���_��ɃR�s�[���Ďg���Ă�������

��Dao�̓Ǝ��g�����\�b�h
public String findBuri_ARGS = "dto,paths";
public List findBuri(BillFindDto dto,List paths);

public String findBuriAndUser_ARGS = "dto,paths,userIDs";
public List findBuriAndUser(BillFindDto dto,List paths,List userIDs);

���p��
paths�Ƃ�
�E������\�L�̃p�X�̃��X�g��n���܂�

userIDs�Ƃ�
�E�Ԃ�����̃��[�UID�����X�g�œn���܂�

StandardBuriProcessor01Test��test01Tx�ɗ��p��Ƃ��ăT���v��������܂��B

�����s���ɂ���
������SQL�̎��s�ɂ͂Ԃ�̃e�[�u�����K�v�ł�
�Ԃ�̎��s�𔺂�Ȃ��ꍇ�͂Ԃ��jar�͕s�v�ł�
