/**
 * 通用js方法封装处理
 */

// 日期格式化
export function parseTime(time: any, pattern?: string) {
	if (arguments.length === 0 || !time)
		return null

	const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
	let date
	if (typeof time === 'object') {
		date = time
	} else {
		if (typeof time === 'string' && /^[0-9]+$/.test(time)) {
			time = parseInt(time)
		} else if (typeof time === 'string') {
			time = time
				.replace(/-/gm, '/')
				.replace('T', ' ')
				.replace(/\.[\d]{3}/gm, '')
		}
		if (typeof time === 'number' && time.toString().length === 10)
			time = time * 1000

		date = new Date(time)
	}
	const formatObj: Record<string, any> = {
		y: date.getFullYear(),
		m: date.getMonth() + 1,
		d: date.getDate(),
		h: date.getHours(),
		i: date.getMinutes(),
		s: date.getSeconds(),
		a: date.getDay(),
	}
	return format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
		let value = formatObj[key]
		// Note: getDay() returns 0 on Sunday
		if (key === 'a')
			return ['日', '一', '二', '三', '四', '五', '六'][value]

		if (result.length > 0 && value < 10)
			value = `0${value}`

		return value || 0
	})
}

// 表单重置
export function resetForm(refName: string) {
	this.$refs[refName]?.resetFields()
}

// 添加日期范围
export function addDateRange(params: any, dateRange: any[], propName?: string) {
	const search = params
	search.params
		= (typeof search.params === 'object' && search.params !== null && !Array.isArray(search.params))
		? search.params
		: {}
	dateRange = Array.isArray(dateRange) ? dateRange : []
	if (typeof propName === 'undefined') {
		search.params.beginTime = dateRange[0]
		search.params.endTime = dateRange[1]
	} else {
		search.params[`begin${propName}`] = dateRange[0]
		search.params[`end${propName}`] = dateRange[1]
	}
	return search
}

// 回显数据字典
export function selectDictLabel(datas: any, value: any) {
	if (value === undefined)
		return ''

	const actions = Object.keys(datas).filter((key: string) => datas[key].value === `${value}`).map((key: string) => datas[key].label)
	if (actions.length === 0)
		actions.push(value)

	return actions.join('')
}

// 回显数据字典（字符串数组）
export function selectDictLabels(datas: any, value: any, separator: any) {
	if (!value?.length)
		return ''

	const currentSeparator = !separator ? ',' : separator
	if (Array.isArray(value))
		value = value.join(currentSeparator)

	const temp = value.split(currentSeparator)
	const actions = Object.keys(temp).reduce((res: string[], current: string) => {
		const arr = Object.keys(datas)
			.filter((key: string) => datas[key].value === `${temp[current]}`)
			.map((key: string) => datas[key].label)
		if (arr.length > 0)
			return [...res, ...arr]

		return [...res, temp[current]]
	}, [])
	return actions.join(currentSeparator)
}

// 字符串格式化(%s )
export function sprintf(str: string) {
	// eslint-disable-next-line prefer-rest-params
	const args = arguments
	let flag = true
	let i = 1
	str = str.replace(/%s/g, () => {
		const arg = args[i++]
		if (typeof arg === 'undefined') {
			flag = false
			return ''
		}
		return arg
	})
	return flag ? str : ''
}

// 转换字符串，undefined,null等转化为""
export function parseStrEmpty(str: string) {
	if (!str || str === 'undefined' || str === 'null')
		return ''

	return str
}

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 */
export function handleTree(data: any, id: any, parentId?: any, children?: any) {
	const config = {
		id: id || 'id',
		parentId: parentId || 'parentId',
		childrenList: children || 'children',
	}

	const childrenListMap: any = {}
	const nodeIds: any = {}
	const tree = []

	for (const d of data) {
		const parentId = d[config.parentId]
		if (childrenListMap[parentId] == null)
			childrenListMap[parentId] = []

		nodeIds[d[config.id]] = d
		childrenListMap[parentId].push(d)
	}

	for (const d of data) {
		const parentId = d[config.parentId]
		if (nodeIds[parentId] == null)
			tree.push(d)
	}

	for (const t of tree)
		adaptToChildrenList(t)

	function adaptToChildrenList(o: any) {
		if (childrenListMap[o[config.id]] !== null)
			o[config.childrenList] = childrenListMap[o[config.id]]

		if (o[config.childrenList]) {
			for (const c of o[config.childrenList])
				adaptToChildrenList(c)
		}
	}

	return tree
}

/**
 * 参数处理
 * @param {*} params  参数
 */
export function tansParams(params: any) {
	let result = ''
	for (const propName of Object.keys(params)) {
		const value = params[propName]
		const part = `${encodeURIComponent(propName)}=`
		if (value !== null && value !== '' && typeof value !== 'undefined') {
			if (typeof value === 'object') {
				for (const key of Object.keys(value)) {
					if (value[key] !== null && value[key] !== '' && typeof value[key] !== 'undefined') {
						const params = `${propName}[${key}]`
						const subPart = `${encodeURIComponent(params)}=`
						result += `${subPart + encodeURIComponent(value[key])}&`
					}
				}
			} else {
				result += `${part + encodeURIComponent(value)}&`
			}
		}
	}
	return result
}

// 返回项目路径
export function getNormalPath(p: any) {
	if (p.length === 0 || !p || p === 'undefined')
		return p

	const res = p.replace('//', '/')
	if (res[res.length - 1] === '/')
		return res.slice(0, res.length - 1)

	return res
}

// 验证是否为blob格式
export function blobValidate(data: any) {
	return data.type !== 'application/json'
}
